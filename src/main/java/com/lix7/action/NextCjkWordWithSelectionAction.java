package main.java.com.lix7.action;

import com.intellij.openapi.editor.actions.TextComponentEditorAction;

public class NextCjkWordWithSelectionAction extends TextComponentEditorAction {
    public NextCjkWordWithSelectionAction(){
        super(new NextPrevCjkWordHandler(true, true, false));
    };
}
