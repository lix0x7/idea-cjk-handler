package main.java.com.lix7.action;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.VisualPosition;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.editor.actions.EditorActionUtil;
import com.intellij.openapi.editor.ex.util.EditorUtil;
import org.jetbrains.annotations.NotNull;

/**
 * handler for cjk words, based on original implementation below:
 * https://upsource.jetbrains.com/idea-ce/file/idea-ce-4d741bc560dd19306d4624d7c8a88aea537f4e6f/platform/platform-impl/src/com/intellij/openapi/editor/actions/NextPrevWordHandler.java
 */
class NextPrevCjkWordHandler extends EditorActionHandler.ForEachCaret {
    private final boolean myNext;
    private final boolean myWithSelection;
    private final boolean myInDifferentHumpsMode;

    NextPrevCjkWordHandler(boolean next, boolean withSelection, boolean inDifferentHumpsMode) {
        myNext = next;
        myWithSelection = withSelection;
        myInDifferentHumpsMode = inDifferentHumpsMode;
    }

    @Override
    protected void doExecute(@NotNull Editor editor, @NotNull Caret caret, DataContext dataContext) {
        if (EditorUtil.isPasswordEditor(editor)) {
            int selectionStartOffset = caret.getLeadSelectionOffset();
            caret.moveToOffset(myNext ? editor.getDocument().getTextLength() : 0);
            if (myWithSelection) caret.setSelection(selectionStartOffset, caret.getOffset());
        }
        else {
            VisualPosition currentPosition = caret.getVisualPosition();
            if (caret.isAtBidiRunBoundary() && (myNext ^ currentPosition.leansRight)) {
                int selectionStartOffset = caret.getLeadSelectionOffset();
                VisualPosition selectionStartPosition = caret.getLeadSelectionPosition();
                caret.moveToVisualPosition(currentPosition.leanRight(!currentPosition.leansRight));
                if (myWithSelection) {
                    caret.setSelection(selectionStartPosition, selectionStartOffset, caret.getVisualPosition(), caret.getOffset());
                }
            }
            else {
                // when the line is in cjk mode, the direction must be left to right
                CjkEditorActionUtil.moveCaretToNextWord(editor, myWithSelection, myInDifferentHumpsMode ^ editor.getSettings().isCamelWords());
            }
        }
    }
}

