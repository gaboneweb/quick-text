package com.bulelani.cli.commands;

import com.bulelani.shared.model.Snippet;
import com.bulelani.shared.service.SnippetService;

public class UpdateCommand extends Command {
    private String expantion;
    private String abbriviation;

    public UpdateCommand(String abbriviation, String expantion) {
        this.abbriviation = abbriviation;
        this.expantion = expantion;
    }

    @Override
    public void execute(SnippetService snippetService) {
        snippetService.updateSnippet(new Snippet(this.expantion, this.abbriviation));
    }

    @Override
    public void execute() {

    }

}
