package com.bulelani.cli.commands;

import com.bulelani.shared.service.SnippetService;

public class DeleteCommand extends Command{
    private String abbriviation;

    public DeleteCommand(String abbreviation){
        this.abbriviation = abbreviation;
    }
    @Override
    public void execute(SnippetService snippetService) {
        snippetService.deleteSnippet(this.abbriviation);
    }

    @Override
    public void execute() {

    }
}
