// $Id: pr341.java,v 1.2 1999/11/04 14:59:47 shields Exp $
// This software is subject to the terms of the IBM Jikes Compiler
// License Agreement available at the following URL:
// http://www.ibm.com/research/jikes.
// Copyright (C) 1996, 1999, International Business Machines Corporation
// and others.  All Rights Reserved.
// You must accept the terms of that agreement to use this software.

class T2
{
	double d1 = 2.0 | Double.MAX_VALUE;  // jikes accepts
	double d2 = 2.0 | Double.MIN_VALUE;  // jikes gives chaos:  * undefined
	on
			this type
}
