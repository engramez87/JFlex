%%

%unicode 2.0
%public
%class UnicodePropList_Line_Separator_2_0

%type int
%standalone

%include src/test/resources/common-unicode-binary-property-java

%%

\p{Line Separator} { setCurCharPropertyValue(); }
[^] { }

<<EOF>> { printOutput(); return 1; }
