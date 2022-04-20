package main.java.com.lix7.action;

import com.intellij.openapi.editor.actions.TextComponentEditorAction;

public class PrevCjkWordWithSelectionAction extends TextComponentEditorAction {
    public PrevCjkWordWithSelectionAction(){
        super(new NextPrevCjkWordHandler(false, true, false));
    };
}
