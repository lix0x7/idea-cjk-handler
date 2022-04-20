package main.java.com.lix7.action;

import com.intellij.codeInsight.navigation.IncrementalSearchHandler;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.VisualPosition;
import com.intellij.openapi.editor.actionSystem.EditorActionManager;
import com.intellij.openapi.editor.actionSystem.TypedAction;
import com.intellij.openapi.editor.actions.MoveCaretLeftAction;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.ui.Messages;
import main.java.com.lix7.typedhandler.TestTypedHandler;
import org.apache.log4j.varia.FallbackErrorHandler;

public class TestAction extends AnAction {

    static {
        EditorActionManager actionManager = EditorActionManager.getInstance();
        TypedAction typedAction = actionManager.getTypedAction();
        typedAction.setupHandler(new TestTypedHandler());
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        CaretModel caretModel = editor.getCaretModel();
        Caret primaryCaret = caretModel.getPrimaryCaret();
        // Get the caret information
        LogicalPosition logicalPos = primaryCaret.getLogicalPosition();
        VisualPosition visualPos = primaryCaret.getVisualPosition();
        int caretOffset = primaryCaret.getOffset();

        // Build and display the caret report.
        String report = logicalPos.toString() + "\n" +
                visualPos.toString() + "\n" +
                "Offset: " + caretOffset;
        Messages.showInfoMessage(report, "Caret Parameters Inside The Editor");
        primaryCaret.moveCaretRelatively(-1, 0, false, false);

    }
}
