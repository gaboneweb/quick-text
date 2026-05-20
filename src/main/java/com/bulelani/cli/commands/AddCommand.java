package com.bulelani.cli.commands;

import com.bulelani.shared.model.Snippet;
import com.bulelani.shared.service.SnippetService;

public class AddCommand extends Command{

    private String expantion;
    private String abbriviation;

    public AddCommand(String abbriviation, String expantion){
        this.abbriviation = abbriviation;
        this.expantion = expantion;
    }
    @Override
    public void execute(SnippetService snippetService) {
        snippetService.addSnippet(new Snippet(this.expantion,this.abbriviation));
    }

    @Override
    public void execute() {

    }
}
