# ZenPad

ZenPad is a beginner-friendly Java program showcase and learning tool built with Java Swing. It is designed to help new programmers explore, run, and understand Java code samples in an interactive and visually clear environment.

## Features

- 📄 **Tabbed Viewer:** Browse and view multiple Java sample programs in tabs.
- 🎨 **Syntax Highlighting:** Powered by RSyntaxTextArea for easy-to-read code.
- 🌗 **Light/Dark Theme Toggle:** Instantly switch between light and dark modes for comfortable viewing.
- 🔠 **Font Zoom:** Increase, decrease, or reset the font size for better readability.
- 🧑‍💻️ **Sample Code Browser:** Easily access a curated collection of beginner-friendly Java examples.
- 📝 **Note Panel:** Save your personal notes and descriptions for the code samples.
- ▶️ **Run Java Code:** Execute sample programs directly within the app (requires JDK).
- 📋 **Copy Code:** Copy code snippets to your clipboard for your own experiments.

## Dependencies

- [Java 17 or higher](https://www.java.com/en/download/manual.jsp)
- [Maven](https://maven.apache.org/install.html) (for building from source)

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

2. **Build the project using Maven:**

    ```sh
    mvn compile
    ```

3. **Run the application:**

    ```sh
    mvn exec:java
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

## Made with ❤️ by Zens

- Bryan Suela
- Jan Conrad Maniquiz
- Marco Jaezzy Bacolto
- Sebastian Abad
- Keiaa (nozomi-75)
