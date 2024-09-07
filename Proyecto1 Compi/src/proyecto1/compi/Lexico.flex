package proyecto1.compi;
import java_cup.runtime.Symbol;
import proyecto1.compi.sym;
%%

%class Lexico
%public
%line
%char
%cup
%unicode

%{
    private int line = 1;
    private int column = 1;
    private int ultimaColumna = 1;

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    private void actualizarPosicion() {
        for (char c : yytext().toCharArray()) {
            if (c == '\n') {
                line++;
                ultimaColumna = column;
                column = 1;
            } else {
                column++;
            }
        }
    }
%}

L=[a-zA-Z]
D=[0-9]
ID=({L}({L}|{D})*)
asciiChar=[\21-\x7E]
space=[ \t\r\n]+
%type Symbol
%%

"CONJ"        { actualizarPosicion(); return new Symbol(sym.Reservada, line, column, yytext()); }
"conjunto"    { actualizarPosicion(); return new Symbol(sym.Reservada, line, column, yytext()); }
"OPERA"       { actualizarPosicion(); return new Symbol(sym.Reservada, line, column, yytext()); }
"operacion"   { actualizarPosicion(); return new Symbol(sym.Reservada, line, column, yytext()); }
"EVALUAR"     { actualizarPosicion(); return new Symbol(sym.Reservada, line, column, yytext()); }
{space}       { actualizarPosicion(); /* Ignorar espacios y saltos de línea */ }
"#"           { actualizarPosicion(); /* Ignorar comentarios */ }
"<!"          { actualizarPosicion(); /* Ignorar comentarios */ }
"!>"          { actualizarPosicion(); /* Ignorar comentarios */ }
":"           { actualizarPosicion(); return new Symbol(sym.DosPuntos, line, column, yytext()); }
"->"          { actualizarPosicion(); return new Symbol(sym.Flecha, line, column, yytext()); }
"~"           { actualizarPosicion(); return new Symbol(sym.Notacion, line, column, yytext()); }
";"           { actualizarPosicion(); return new Symbol(sym.PuntoYComa, line, column, yytext()); }
","           { actualizarPosicion(); return new Symbol(sym.Coma, line, column, yytext()); }
"{"           { actualizarPosicion(); return new Symbol(sym.LlaveAbrir, line, column, yytext()); }
"}"           { actualizarPosicion(); return new Symbol(sym.LlaveCerrar, line, column, yytext()); }
"("           { actualizarPosicion(); return new Symbol(sym.ParentesisAbrir, line, column, yytext()); }
")"           { actualizarPosicion(); return new Symbol(sym.ParentesisCerrar, line, column, yytext()); }
"U"           { actualizarPosicion(); return new Symbol(sym.Union, line, column, yytext()); }
"&"           { actualizarPosicion(); return new Symbol(sym.Interseccion, line, column, yytext()); }
"^"           { actualizarPosicion(); return new Symbol(sym.Complemento, line, column, yytext()); }
"-"           { actualizarPosicion(); return new Symbol(sym.Diferencia, line, column, yytext()); }
{asciiChar}   { actualizarPosicion(); return new Symbol(sym.Simbolo, line, column, yytext()); }
{ID}          { actualizarPosicion(); return new Symbol(sym.ID, line, column, yytext()); }
.             { 
                actualizarPosicion();
                System.out.println("Lexical error: " + yytext() + " línea: " + line + " columna: " + column); 
              }
