#!/bin/bash

set -e

# Build the project as Jar
echo "Building project with Maven..."
mvn clean package

# Check if Maven build was successful
if [ $? -ne 0 ]; then
    echo "Maven build failed."
    exit 1
fi

# Find JAR file
JAR_FILE=$(find target -maxdepth 1 -name '*-jar-with-dependencies.jar' -print -quit)

if [[ -z "$JAR_FILE" ]]; then
    echo "Error: JAR file not found after Maven build."
    exit 1
fi

JAR_NAME=$(basename "$JAR_FILE")

# Make an installable package
echo "Creating installable package with jpackage..."
jpackage \
  --name ZenPad \
  --app-version 1.2-SNAPSHOT \
  --input target \
  --main-jar "$JAR_NAME" \
  --main-class zenpad.core.ZenPad \
  --dest . \
  --icon src/main/resources/icons/64x64.png \
  --linux-shortcut \
  --linux-package-name zenpad \
  --vendor "Zens" \
  --description "A beginner-friendly tool for exploring and learning code." \
  --copyright "©2025 Zens." \
  --license-file LICENSE

JPACKAGE_EXIT_CODE=$?

if [[ $JPACKAGE_EXIT_CODE -ne 0 ]]; then
    echo "Error: jpackage failed with exit code $JPACKAGE_EXIT_CODE. See above messages for details."
    exit 1
fi

# Find the generated package file
PKG_FILE=$(find . -maxdepth 1 -name 'zenpad*' -print -quit)

# Check if PKG_FILE variable is not empty
if [[ -n "$PKG_FILE" ]]; then
    echo "Package built successfully: $PKG_FILE"
else
    echo "Error: jpackage reported success, but no 'zenpad' package file found in the current directory."
    echo "This might indicate a partial failure or a mismatch in expected output."
    exit 1
fi