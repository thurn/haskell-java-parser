// $Id: pr236a.java,v 1.5 1999/11/04 14:59:45 shields Exp $
// This software is subject to the terms of the IBM Jikes Compiler
// License Agreement available at the following URL:
// http://www.ibm.com/research/jikes.
// Copyright (C) 1996, 1999, International Business Machines Corporation
// and others.  All Rights Reserved.
// You must accept the terms of that agreement to use this software.

class SuperC {
	public String name = "noname";
	public SuperC() {
		name = "Bernd";
		init();
		System.out.println( "SuperC() name="+name );
	}
	public void init() {}
}
