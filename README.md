# QuickText — CLI Snippet Expander

```
 ██████╗ ██╗   ██╗██╗ ██████╗██╗  ██╗    ████████╗███████╗██╗  ██╗████████╗
██╔═══██╗██║   ██║██║██╔════╝██║ ██╔╝    ╚══██╔══╝██╔════╝╚██╗██╔╝╚══██╔══╝
██║   ██║██║   ██║██║██║     █████╔╝        ██║   █████╗   ╚███╔╝    ██║   
██║▄▄ ██║██║   ██║██║██║     ██╔═██╗        ██║   ██╔══╝   ██╔██╗    ██║   
╚██████╔╝╚██████╔╝██║╚██████╗██║  ██╗       ██║   ███████╗██╔╝ ██╗   ██║   
 ╚══▀▀═╝  ╚═════╝ ╚═╝ ╚═════╝╚═╝  ╚═╝       ╚═╝   ╚══════╝╚═╝  ╚═╝   ╚═╝  
```

QuickText is a system-wide text expander for macOS, Windows, and Linux. Type a short abbreviation anywhere, press Space, and it instantly expands into the full text — emails, signatures, common phrases, anything you want.

It ships as two separate JARs:

| Component | Description |
|---|---|
| `quick-text-cli.jar` | Command-line tool to manage your snippets |
| `quick-text-daemon.jar` | Background process that listens for keystrokes and performs expansions |

---

## Features

- **System-wide expansion** — works in any application (browser, IDE, chat, terminal)
- **SQLite-backed storage** — snippets persist across sessions in `~/.typinator/snippets.db`
- **Cross-platform** — automatically uses the correct paste shortcut (`Cmd+V` on macOS, `Ctrl+V` elsewhere)
- **Simple CLI** — add, update, delete, and list snippets from the terminal

---

## Requirements

- Java 17 or later
- The daemon requires accessibility/input monitoring permissions on macOS

---

## Installation

1. Download `quick-text-cli.jar` and `quick-text-daemon.jar`.
2. Place them somewhere on your `PATH`, or run them directly with `java -jar`.
3. The app directory `~/.typinator/` is created automatically on first run.

---

## CLI Usage

```
java -jar quick-text-cli.jar <command> [arguments]
```

### Commands

| Command | Description |
|---|---|
| `add <abbreviation> <expansion>` | Add a new snippet |
| `update <abbreviation> <expansion>` | Update an existing snippet's expansion |
| `delete <abbreviation>` | Delete a snippet |
| `list` | List all snippets in a formatted table |
| `help` | Show help information |

### Examples

```bash
# Add snippets
java -jar quick-text-cli.jar add :em "hello@example.com"
java -jar quick-text-cli.jar add :sig "Kind regards, Bulelani"
java -jar quick-text-cli.jar add brb "Be right back"

# Update a snippet
java -jar quick-text-cli.jar update :em "newemail@example.com"

# Delete a snippet
java -jar quick-text-cli.jar delete :em

# List all snippets
java -jar quick-text-cli.jar list
```

> **Tip:** Wrap expansions that contain spaces in double quotes.

---

## Running the Daemon

The daemon runs in the background and monitors your keystrokes globally. Start it separately:

```bash
java -jar quick-text-daemon.jar
```

Once running, type any saved abbreviation in any application and press **Space** — it will be replaced with the full expansion automatically.

---

## How It Works

```
Keystrokes → KeyBoardListener → KeyStrokeBuffer
                                      │
                              Space pressed?
                                      │
                              ExpansionService.matchesAbbreviation()
                                      │
                              Match found → delete abbreviation → paste expansion
```

1. `KeyBoardListener` captures every key typed system-wide via [JNativeHook](https://github.com/kwhat/jnativehook).
2. Characters are accumulated in a `KeyStrokeBuffer`.
3. When Space is pressed, the buffer content is checked against stored snippets.
4. If a match is found, `ExpansionService` deletes the typed abbreviation (via backspace simulation) and pastes the expansion using the system clipboard.

---

## Project Structure

```
com.bulelani
├── cli
│   ├── Args.java                    # Parses raw command-line arguments
│   ├── CommandLineHandler.java      # Entry point for CLI argument handling
│   └── commands
│       ├── Command.java             # Abstract base; factory method for command routing
│       ├── AddCommand.java          # Adds a snippet
│       ├── UpdateCommand.java       # Updates a snippet
│       ├── DeleteCommand.java       # Deletes a snippet
│       ├── ListCommand.java         # Lists all snippets (ASCII table)
│       └── HelpCommand.java         # Prints help/usage
├── daemon
│   ├── Daemon.java                  # Daemon entry point
│   ├── KeyBoardListener.java        # JNativeHook key event handler
│   ├── KeyStrokeBuffer.java         # Accumulates typed characters
│   └── ExpansionService.java        # Matches & expands abbreviations
└── shared
    ├── AppConfig.java               # App directory and DB path constants
    ├── OS.java                      # OS detection utility
    ├── model
    │   └── Snippet.java             # Snippet model (abbreviation + expansion)
    ├── repository
    │   ├── ISnippetRepository.java          # Repository interface
    │   ├── InMemorySnippetRepository.java   # In-memory implementation (testing)
    │   └── SQLiteSnippetRepository.java     # SQLite-backed implementation
    └── service
        └── SnippetService.java      # Service layer wrapping the repository
```

---

## Data Storage

Snippets are stored in a SQLite database at:

```
~/.typinator/snippets.db
```

Schema:

```sql
CREATE TABLE snippets (
    id           INTEGER PRIMARY KEY AUTOINCREMENT,
    abbreviation TEXT    NOT NULL UNIQUE,
    expansion    TEXT    NOT NULL,
    created_at   TEXT    NOT NULL DEFAULT (datetime('now')),
    updated_at   TEXT    NOT NULL DEFAULT (datetime('now'))
);
```

---

## License

MIT
