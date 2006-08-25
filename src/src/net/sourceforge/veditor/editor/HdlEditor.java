/*******************************************************************************
 * Copyright (c) 2004, 2006 KOBAYASHI Tadashi and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    KOBAYASHI Tadashi - initial API and implementation
 *******************************************************************************/

package net.sourceforge.veditor.editor;

import net.sourceforge.veditor.VerilogPlugin;
import net.sourceforge.veditor.actions.CompileAction;
import net.sourceforge.veditor.actions.FormatAction;
import net.sourceforge.veditor.actions.GotoMatchingBracketAction;
import net.sourceforge.veditor.actions.OpenDeclarationAction;
import net.sourceforge.veditor.parser.Module;
import net.sourceforge.veditor.parser.ModuleList;
import net.sourceforge.veditor.parser.ParseException;
import net.sourceforge.veditor.parser.ParserManager;
import net.sourceforge.veditor.parser.Segment;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.TextOperationAction;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

/**
 *  main class
 */
abstract public class HdlEditor extends TextEditor
{
	private ColorManager colorManager;
	private HdlContentOutlinePage outlinePage;
	private ModuleHierarchyPage modulePage;

	private static HdlEditor current;
	public static HdlEditor current()
	{
		return current;
	}

	public HdlEditor()
	{
		super();

		current = this;
		colorManager = new ColorManager();
		HdlTextAttribute.init();
	}

	public void updatePartControl(IEditorInput input)
	{
		super.updatePartControl(input);
		setInputPages(input);
	}

	protected void initializeKeyBindingScopes()
	{
		setKeyBindingScopes(new String[] { "net.sourceforge.veditor.scope" });
	}

	public IDocument getDocument()
	{
		return getDocumentProvider().getDocument(getEditorInput());
	}

	abstract public String getEditorId();

	protected void createActions()
	{
		super.createActions();

		// add content assist action
		IAction action;
		action =
			new TextOperationAction(
				EditorMessages.getResourceBundle(),
				"ContentAssistProposal.",
				this,
				ISourceViewer.CONTENTASSIST_PROPOSALS);
		action.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
		setAction("ContentAssistProposal", action);

		// add special actions
		setAction("GotoMatchingBracket", new GotoMatchingBracketAction());
		setAction("OpenDeclaration", new OpenDeclarationAction());
		setAction("Format", new FormatAction());
		setAction("Compile", new CompileAction());
	}

//	for content assist?
//	public void editorContextMenuAboutToShow(MenuManager menu)
//	{
//		super.editorContextMenuAboutToShow(menu);
//		addAction(menu, "ContentAssistProposal");
//		addAction(menu, "ContentAssistTip");
//	}

	public void dispose()
	{
		colorManager.dispose();
		setInputPages(null);
		super.dispose();
	}

	public void doRevertToSaved()
	{
		super.doRevertToSaved();
		updatePages();
	}

	public void doSave(IProgressMonitor monitor)
	{
		super.doSave(monitor);
		updatePages();
	}

	public void doSaveAs()
	{
		super.doSaveAs();
		updatePages();
	}
	
	/**
	 * update outline and module hierarchy page
	 */
	private void updatePages()
	{
		checkSyntax();
		if (outlinePage != null)
			outlinePage.update();
		if (modulePage != null)
			modulePage.update();
	}

	private void setInputPages(IEditorInput input)
	{
		if (outlinePage != null)
			outlinePage.setInput(input);
		if (modulePage != null)
			modulePage.setInput(input);
	}
	
	private void checkSyntax()
	{
		HdlDocument doc = getHdlDocument();
		ParserManager manager = doc.createParserManager();

		// check for non-workspace file
		IFile file = doc.getFile();
		if (file == null)
			return;

		VerilogPlugin.clearProblemMarker(file);
		try
		{
			manager.parseSyntax();
		}
		catch (ParseException e)
		{
			VerilogPlugin.println(doc.getFile().toString());
			VerilogPlugin.println(e.toString());
			VerilogPlugin.setErrorMarker(doc.getFile(),
					e.currentToken.beginLine, e.toString());
			update();
		}
	}
	
	/**
	 * update editor view for problem marker.
	 */
	public void update()
	{
		try
		{
			StyledText widget = getViewer().getTextWidget();
			int caret = widget.getCaretOffset();
			int top = widget.getTopIndex();
			
			super.doSetInput(getEditorInput());
			
			// widget might change in doSetInput
			widget = getViewer().getTextWidget();
			widget.setSelection(caret);
			widget.setTopIndex(top);
		}
		catch (CoreException e)
		{
		}
	}
	
	public void doSetInput(IEditorInput input) throws CoreException
	{
		super.doSetInput(input);
		setInputPages(input);
	}

	public Object getAdapter(Class required)
	{
		// System.out.println("HdlEditor.getAdapter : " + required);
		if (IContentOutlinePage.class.equals(required))
		{
			if (outlinePage == null)
			{
				outlinePage = new HdlContentOutlinePage(this);
				if (getEditorInput() != null)
					outlinePage.setInput(getEditorInput());
			}
			return outlinePage;
		}
		else if (ModuleHierarchyPage.class.equals(required))
		{
			if (modulePage == null)
			{
				if (getHdlDocument().getProject() != null)
				{
					modulePage = new ModuleHierarchyPage(this);
					if (getEditorInput() != null)
						modulePage.setInput(getEditorInput());
				}
			}
			return modulePage;
		}
		return super.getAdapter(required);
	}

	public HdlDocument getHdlDocument()
	{
		IDocument doc = getDocument();
		if (doc instanceof HdlDocument)
			return (HdlDocument)doc;
		else
			return null;
	}

	protected void initializeEditor()
	{
		super.initializeEditor();

//		for content assist?
//		setEditorContextMenuId("#EditorContext");
//		setRulerContextMenuId("#RulerContext");
	}

	protected void editorContextMenuAboutToShow(IMenuManager menu)
	{
		super.editorContextMenuAboutToShow(menu);
		menu.add(new Separator());
		menu.add(getAction("OpenDeclaration"));
	}

	public void beep()
	{
		Display.getCurrent().beep();
	}
	public ISourceViewer getViewer()
	{
		return getSourceViewer();
	}

	private Segment[] parse()
	{
		HdlDocument doc = getHdlDocument();
		if (doc != null)
		{
			ParserManager manager = doc.createParserManager();
			manager.parse();

			int size = manager.size();
			Segment[] elements = new Segment[size];
			for (int i = 0; i < size; i++)
				elements[i] = manager.getModule(i);

			manager.dispose();
			return elements;
		}
		return null;
	}

	/**
	 * open new editor page by module name
	 * @param modName	module name
	 * @param file		file which defines the module
	 */
	public void openPage(String modName, IFile file)
	{
		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		try
		{
			IEditorPart editorPart = page.openEditor(new FileEditorInput(file),
					getEditorId());
					
			if (editorPart instanceof HdlEditor)
			{
				HdlEditor editor = (HdlEditor)editorPart;
				Segment[] modules = editor.parse();
				if (modules != null)
				{
					for (int i = 0; i < modules.length; i++)
					{
						Segment mod = modules[i];
						if (modName.equals(mod.toString()))
						{
							IDocument doc = editor.getDocument();
							int line = mod.getLine() - 1;
							int start = doc.getLineOffset(line);
							ISourceViewer viewer = editor.getSourceViewer();
							viewer.setTopIndex(start);
							viewer.getTextWidget().setSelection(start);
						}
					}
				}
			}
		}
		catch (BadLocationException e)
		{
		}
		catch (PartInitException e)
		{
			System.out.println(e);
		}
	}

	/**
	 * open new editor page by module name
	 * @param modName module name
	 */
	public void openPage(String modName)
	{
		ModuleList mlist = ModuleList.find(getHdlDocument().getProject());
		Module mod = mlist.findModule(modName);
		if (mod == null)
		{
			beep();
			return;
		}
		openPage(modName, mod.getFile());
	}

	/**
	 * @return Returns the colorManager.
	 */
	public ColorManager getColorManager()
	{
		return colorManager;
	}
}


