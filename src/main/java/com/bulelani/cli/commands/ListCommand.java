package com.bulelani.cli.commands;

import com.bulelani.shared.model.Snippet;
import com.bulelani.shared.service.SnippetService;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.asciithemes.a7.A7_Grids;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

import java.util.List;

public class ListCommand extends Command{

    public ListCommand(){
    }
    @Override
    public void execute(SnippetService snippetService) {
        List<Snippet> snippets = snippetService.getAll();
        AsciiTable table = new AsciiTable();

        table.addRule();
        table.addRow("Abbreviation", "Expansion");
        table.addRule();

        if (snippets.isEmpty()) {
            table.addRow("No snippets found.", "");
            table.addRule();
        } else {
            for (Snippet snippet : snippets) {
                table.addRow(snippet.getAbbreviation(), snippet.getExpansion().replace("\n", "<br/>"));
                table.addRule();  // border after every row
            }
        }

        table.setTextAlignment(TextAlignment.LEFT);
        table.getContext().setGrid(A7_Grids.minusBarPlus());
        System.out.println(table.render());
    }

}
