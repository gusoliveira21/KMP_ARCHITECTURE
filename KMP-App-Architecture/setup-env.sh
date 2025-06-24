#!/bin/bash

# Script de configuração do ambiente para o projeto KMP-App-Architecture

echo "🔧 Configurando ambiente do projeto..."

# Configurar JAVA_HOME para Java 17
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
echo "✅ JAVA_HOME configurado: $JAVA_HOME"

# Configurar Android SDK (se existir)
if [ -d "$HOME/Library/Android/sdk" ]; then
    export ANDROID_HOME="$HOME/Library/Android/sdk"
    export ANDROID_SDK_ROOT="$HOME/Library/Android/sdk"
    echo "✅ Android SDK configurado: $ANDROID_HOME"
else
    echo "⚠️  Android SDK não encontrado em $HOME/Library/Android/sdk"
fi

# Adicionar Android SDK ao PATH
if [ -d "$ANDROID_HOME" ]; then
    export PATH="$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools"
    echo "✅ Android SDK adicionado ao PATH"
fi

# Verificar se o Gradle está usando a versão correta do Java
echo "🔍 Verificando versão do Java..."
java -version

echo "🔍 Verificando versão do Gradle..."
./gradlew --version

echo "🎉 Ambiente configurado! Agora você pode executar:"
echo "   bundle exec fastlane screenshots" 