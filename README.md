# ZenPad

ZenPad is a beginner-friendly Java program showcase and learning tool built with Java Swing. It is designed to help new programmers explore, run, and understand Java code samples in an interactive and visually clear environment.

## Features

- 📄 **Tabbed Viewer:** Browse and view multiple Java sample programs in tabs.
- 🎨 **Syntax Highlighting:** Powered by RSyntaxTextArea for easy-to-read code.
- 🌗 **Light/Dark Theme Toggle:** Instantly switch between light and dark modes for comfortable viewing.
- 🔠 **Font Zoom:** Zoom in, zoom out, or reset the font size for better readability.
- 📝 **Sample Code Browser:** Easily access a curated collection of beginner-friendly Java examples.
- ▶️ **Run Java Code:** Execute sample programs directly within the app (requires JDK).
- 📋 **Copy Code:** Copy code snippets to your clipboard for your own experiments.

## Getting Started

### Prerequisites

- Java 17 or later (JDK)
- Maven (for building from source)

### Running

1. **Clone the repository:**
    ```sh
    git clone https://github.com/nozomi-75/zenpad.git
    cd zenpad
    ```

2. **Build the project:**
    ```sh
    mvn clean package
    ```

3. **Run from source:**
    ```sh
    mvn exec:java -Dexec.mainClass="zenpad.MainApp"
    ```

## Project Structure

- `src/main/java/zenpad/` — Main Java source code
- `src/main/resources/samples/` — Java sample files for learning
- `src/main/resources/icons/` — Application icons

## Customization

- **Add your own samples:** Place `.java` files in `src/main/resources/samples/`.
- **Change icons:** Replace images in `src/main/resources/icons/`.

## Credits

- [RSyntaxTextArea](https://github.com/bobbylight/RSyntaxTextArea) for syntax highlighting
- [FlatLaf](https://www.formdev.com/flatlaf/) for modern look and feel
- [JetBrains Mono](https://github.com/JetBrains/JetBrainsMono) for monospace font
- [Fira Code](https://github.com/tonsky/FiraCode) for alternate font

## License

MIT License. See [LICENSE](LICENSE) for details.

---

**Made with ❤️ by Zens**