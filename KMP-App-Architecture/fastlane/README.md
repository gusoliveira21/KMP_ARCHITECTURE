fastlane documentation
----

# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```sh
xcode-select --install
```

For _fastlane_ installation instructions, see [Installing _fastlane_](https://docs.fastlane.tools/#installing-fastlane)

# Available Actions

## Android

### android android_tests

```sh
[bundle exec] fastlane android android_tests
```

Executa os testes unitários do Android

### android screenshots

```sh
[bundle exec] fastlane android screenshots
```

Executa testes de UI e captura screenshots

### android screenshots_ci

```sh
[bundle exec] fastlane android screenshots_ci
```

Executa testes de UI em CI/CD (requer dispositivos)

### android build_tests

```sh
[bundle exec] fastlane android build_tests
```

Executa apenas a compilação dos testes

### android ci

```sh
[bundle exec] fastlane android ci
```

Executa o pipeline de CI para pull requests

----

This README.md is auto-generated and will be re-generated every time [_fastlane_](https://fastlane.tools) is run.

More information about _fastlane_ can be found on [fastlane.tools](https://fastlane.tools).

The documentation of _fastlane_ can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
