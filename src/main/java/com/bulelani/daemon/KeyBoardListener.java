package com.bulelani.daemon;

import com.bulelani.shared.model.Snippet;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import java.util.Optional;

public class KeyBoardListener implements NativeKeyListener {
    private final KeyStrokeBuffer strokeBuffer;
    private final ExpansionService expansionService;

    public KeyBoardListener(KeyStrokeBuffer buffer, ExpansionService expansionService){
        this.strokeBuffer = buffer;
        this.expansionService = expansionService;
    }

    public KeyBoardListener(ExpansionService expansionService){
        this(new KeyStrokeBuffer(),expansionService);
    }

    /**
     * @param nativeEvent
     * Handles the event for when a key is pressed
     * The space character is ignored,as well as when expansionService is expanding the text.
     */
    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeEvent) {
        if (Character.isISOControl(nativeEvent.getKeyChar()) || Character.isSpaceChar(nativeEvent.getKeyChar())) {
            return;
        }

        if (this.expansionService.isExpanding()){
            this.expansionService.toogleExpanding();
            return;
        }
        this.strokeBuffer.append(nativeEvent.getKeyChar());
    }

    /**
     * @param nativeEvent
     * Handles key pressed events.
     */
    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
        switch (nativeEvent.getKeyCode()){
            case NativeKeyEvent.VC_BACKSPACE:
                strokeBuffer.removeLast();
                break;
            case NativeKeyEvent.VC_SPACE:
                Optional<Snippet> op = expansionService.matchesAbbreviation(strokeBuffer.get());
                op.ifPresent(expansionService::expand);
                strokeBuffer.close();
                break;
        }
    }

}
