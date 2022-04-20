package main.java.com.lix7.action;

import com.intellij.openapi.editor.actions.TextComponentEditorAction;

public class PrevCjkWordAction extends TextComponentEditorAction {
    public PrevCjkWordAction(){
        super(new NextPrevCjkWordHandler(false, false, false));
    };
}
