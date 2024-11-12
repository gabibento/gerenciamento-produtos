package com.loja.ui;

/**
 * Classe utilitária que fornece constantes para aplicar cores ao texto exibido no console.
 *
 * Utiliza códigos de escape ANSI para alterar as cores do texto no console.
 * Esta classe permite que o texto seja exibido em diferentes cores e restaura
 * a cor padrão com a constante RESET. As cores disponíveis são: vermelho, verde,
 * amarelo e azul.
 */
public class CoresConsole {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
}

