/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2001       Gerwin Klein <lsf@jflex.de>                    *
 * Copyright (C) 2001       Bernhard Rumpe <rumpe@in.tum.de>               *
 * All rights reserved.                                                    *
 *                                                                         *
 * This program is free software; you can redistribute it and/or modify    *
 * it under the terms of the GNU General Public License. See the file      *
 * COPYRIGHT for more information.                                         *
 *                                                                         *
 * This program is distributed in the hope that it will be useful,         *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of          *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the           *
 * GNU General Public License for more details.                            *
 *                                                                         *
 * You should have received a copy of the GNU General Public License along *
 * with this program; if not, write to the Free Software Foundation, Inc., *
 * 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA                 *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


import java.util.*;

/**
 * Symbol table for the interpreter, contains information about
 * variables and functions.
 * 
 * For every binding location of a name a symbol will be created.
 * The symbol tables are connected hierarchically by pointers to
 * the predecessor. Lookup takes predecessors into account.
 */ 
public class SymTab {
  Map<String,SymtabEntry> m;   // contains the list of words
  SymTab pred;  // predecessor symbol table (if exists)

  public SymTab() {
    this(null); 
  }

  public SymTab(SymTab p) {
    m = new HashMap<String,SymtabEntry>();
    pred = p;
  }

  public boolean enter(String s, SymtabEntry e) {
    Object value = lookup(s);
    m.put(s, e);
    return(value==null);
  }

  public SymtabEntry lookup(String s) {
    Object value = m.get(s);
    if (value==null && pred!=null)
      value = pred.lookup(s);
    return ((SymtabEntry)value);
  }

  public String toString() {    // for output with print
    StringBuilder res = new StringBuilder("symbol table\n=============\n");
    
    for (Map.Entry<String,SymtabEntry> entry : m.entrySet())
      res.append(entry.getKey()).append("   \t").append(entry.getValue()).append("\n");

    if (pred != null)
      res.append("++ predecessor!\n");

    return res.toString();
  }

  public int size() {
    return(m.size()); 
  }
}
