# Setup do Projeto KMP-App-Architecture

## Pré-requisitos

- Java 17 (OpenJDK)
- Android SDK
- Ruby (para Fastlane)
- Gradle

## Configuração Rápida

### 1. Executar o script de setup
```bash
source setup-env.sh
```

### 2. Verificar se tudo está configurado
```bash
java -version  # Deve mostrar Java 17
./gradlew --version  # Deve usar Java 17
adb devices  # Deve listar dispositivos/emuladores
```

## Configuração Manual

### Java 17
O projeto está configurado para usar Java 17 no `gradle.properties`:
```properties
org.gradle.java.home=/opt/homebrew/Cellar/openjdk@17/17.0.15/libexec/openjdk.jdk/Contents/Home
```

### Variáveis de Ambiente
Configure no seu shell (adicione ao `~/.zshrc` ou `~/.bash_profile`):
```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
export ANDROID_HOME=$HOME/Library/Android/sdk
export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools
```

## Executando Testes

### Testes Locais (sem dispositivos)
```bash
bundle exec fastlane screenshots
```

### Testes com Emulador/Dispositivo
1. Inicie um emulador ou conecte um dispositivo
2. Execute:
```bash
bundle exec fastlane screenshots
```

### Apenas Compilação
```bash
bundle exec fastlane build_tests
```

## Troubleshooting

### Erro de Java 8
Se aparecer erro sobre Java 8, execute:
```bash
source setup-env.sh
```

### Emulador não encontrado
Verifique se o emulador está rodando:
```bash
adb devices
```

### Fastlane não encontrado
Instale as dependências:
```bash
bundle install
``` 