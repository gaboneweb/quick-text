package com.bulelani.daemon;

import com.bulelani.shared.OS;
import com.bulelani.shared.model.Snippet;
import com.bulelani.shared.repository.ISnippetRepository;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Optional;

public class ExpansionService {

    private final ISnippetRepository repository;
    private final Robot robot;
    private final Clipboard clipboard;

    private volatile boolean isExpanding = false;

    public ExpansionService(ISnippetRepository repository) throws AWTException {
        this.repository = repository;
        this.robot = new Robot();
        this.clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    public boolean isExpanding() {
        return isExpanding;
    }

    public void toogleExpanding(){
        this.isExpanding = !this.isExpanding;
    }

    public void expand(Snippet snippet) {
        this.isExpanding = true;
        deleteAbbreviation(snippet.getAbbreviation());
        pasteExpansion(snippet.getExpansion());
    }

    private void deleteAbbreviation(String abbreviation) {
        for (int i = 0; i <= abbreviation.length(); i++) {
            robot.keyPress(KeyEvent.VK_BACK_SPACE);
            robot.keyRelease(KeyEvent.VK_BACK_SPACE);
            robot.delay(1);
        }
    }

    public Optional<Snippet> matchesAbbreviation(String buffer) {
        return repository.findByShortcut(buffer);
    }

    public void pasteExpansion(String text) {
        clipboard.setContents(new StringSelection(text), null);
        robot.delay(100);

        int modifier = OS.isMac() ? KeyEvent.VK_META : KeyEvent.VK_CONTROL;

        robot.keyPress(modifier);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(modifier);

        robot.delay(100);
    }

}
