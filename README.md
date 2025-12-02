# libraryBackend

<div align = "center">

<img src = "/resources/library_logo.png" alt = "libraryBackend logo" title = "ribbit">

`libraryBackend` is a small REST API backend implementation for a library system.

</div>

## Table of Contents :pushpin:

* [Getting Started](#getting-started-fire)
    - [Java 21](#java-21)
    - [Maven 3.9.11](#maven-3911)
* [Usage](#usage-frog)

## Getting Started :fire:

Before running `libraryBackend`on your computer, you might need to install the following dependencies:

### Java 21

Make sure you have Java 21 (or higher) installed beforehand. If not, you can install it from its official [Oracle page](https://www.oracle.com/java/technologies/downloads/).

### Maven 3.9.11

You may install the package on its official [website](https://maven.apache.org/download.cgi).

## Usage :frog:

With all the prerequisites installed, run the following command on your shell to build the project:

```shell
mvn clean install
```

After the build is sucessfully finished, start running the backend on your local server:

```shell
mvn spring-boot:run
```

Then open Swagger UI and you are ready to start writing your HTTP requests!