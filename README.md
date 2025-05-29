# ZenPad

ZenPad is a beginner-friendly Java program showcase and learning tool built with Java Swing. It is designed to help new programmers explore, run, and understand Java code samples in an interactive and visually clear environment. 

ZenPad requires JDK 17 or higher to run. Other language support may require their specific software.

## Features

- 📄 **Tabbed Viewer:** Browse and view multiple Java sample programs in tabs.
- 🎨 **Syntax Highlighting:** Powered by RSyntaxTextArea for easy-to-read code.
- 🌗 **Theme Toggle:** Instantly switch between light and dark modes for comfortable viewing.
- 🔠 **Font Zoom:** Increase, decrease, or reset the font size for better readability.
- 🧑‍💻️ **Code Browser:** Easily access a curated collection of beginner-friendly Java examples.
- 📝 **Note Panel:** Save your personal notes and descriptions for the code samples.
- ▶️ **Run Sample Code:** Execute sample programs directly within the app.
- 📋 **Copy Code:** Copy code snippets to your clipboard for your own experiments.

## Running

1. [Download the latest ZenPad JAR from the Releases page](https://github.com/nozomi-75/ZenPad/releases).
2. Run the application:

    ```sh
    java -jar ZenPad.jar
    ```

   Alternatively, you may mark the file as executable and run it as a program.

## Building from Source

1. **Clone the repository:**

    ```sh
    git clone https://github.com/nozomi-75/ZenPad.git
    cd ZenPad
    ```

2. **Build the project and create the JAR with dependencies using Maven:**

    ```sh
    mvn clean package
    ```

    The JAR file with all dependencies will be generated at `target/ZenPad-1.2-SNAPSHOT-jar-with-dependencies.jar`.

3. **Run the generated JAR:**

    ```sh
    java -jar target/ZenPad-1.2-SNAPSHOT-jar-with-dependencies.jar
    ```

## Project Structure

- `src/main/java/zenpad/` — Main Java source code
- `src/main/resources/samples/` — Sample files for learning
- `src/main/resources/notes/` — Markdown notes for sample files
- `src/main/resources/icons/` — Application icons

## Customization

> Adding more samples and notes will be better supported in the future 1.3 release.
- **Change icons:** Replace images in `src/main/resources/icons/`. 

## Credits

- [FlatLaf](https://www.formdev.com/flatlaf/) is used for the look and feel of the program.
- [JetBrains Mono](https://github.com/JetBrains/JetBrainsMono) is used for the monospace font.
- [RSyntaxTextArea](https://github.com/bobbylight/RSyntaxTextArea) is used for syntax highlighting.

## License

MIT License. See [LICENSE](LICENSE) for details.

## Made with ❤️ by Zens

- Bryan Suela
- Jan Conrad Maniquiz
- Marco Jaezzy Bacolto
- Sebastian Abad
- Keiaa (nozomi-75)
