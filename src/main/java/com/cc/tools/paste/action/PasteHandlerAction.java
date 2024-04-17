package com.cc.tools.paste.action;

import com.cc.tools.paste.utils.CuttingBoardUtil;
import com.cc.tools.paste.utils.StringUtils;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformCoreDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

/**
 * <p>
 *     点击粘贴后的事件类
 * </p>
 */
public class PasteHandlerAction extends AnAction {
    /**
     * Implement this method to provide your action handler.
     *
     * @param e Carries information on the invocation place
     */
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        String clipboardForText = CuttingBoardUtil.getClipboardForText();
        //判断当前文本是否是下划线
        if(StringUtils.isSnakeCase(clipboardForText)){
            String camelCaseStr = StringUtils.snakeCaseToCamelCase(clipboardForText);
            Editor editor = e.getData(PlatformCoreDataKeys.EDITOR);
            if(editor != null){
                //获取到光标位置
                int cursorPosition = editor.getCaretModel().getOffset();
                insertTextToCursorPosition(camelCaseStr, editor, cursorPosition);
            }
        }
    }

    /**
     * 将文本插入到光标位置
     * @param camelCaseStr 待插入的文本
     * @param editor 编辑器实例
     * @param cursorPosition 光标位置
     */
    private void insertTextToCursorPosition(String camelCaseStr, Editor editor, int cursorPosition) {

        Project project = editor.getProject();
        if(project != null){
            WriteCommandAction.runWriteCommandAction(project,()->{
                editor.getDocument().insertString(cursorPosition, camelCaseStr);
                editor.getCaretModel().moveToOffset(cursorPosition + camelCaseStr.length());
            });
        }
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        VirtualFile vf = e.getData(PlatformCoreDataKeys.VIRTUAL_FILE);
        /**
         * 如果在已打开的文件中并且剪切板存在数据才启用
         */
        if(vf != null && CuttingBoardUtil.checkClipboardForText()){
            e.getPresentation().setEnabled(true);
        }else{
            e.getPresentation().setEnabled(false);
        }
    }
}
