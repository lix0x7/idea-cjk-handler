package main.java.com.lix7.action;

import com.intellij.openapi.editor.actions.TextComponentEditorAction;

public class NextCjkWordAction extends TextComponentEditorAction {
    public NextCjkWordAction(){
        super(new NextPrevCjkWordHandler(true, false, false));
    };
}
