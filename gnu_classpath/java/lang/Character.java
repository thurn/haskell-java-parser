/* java.lang.Character -- Wrapper class for char, and Unicode subsets
   Copyright (C) 1998, 1999, 2001, 2002 Free Software Foundation, Inc.

This file is part of GNU Classpath.

GNU Classpath is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.

GNU Classpath is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with GNU Classpath; see the file COPYING.  If not, write to the
Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
02111-1307 USA.

Linking this library statically or dynamically with other modules is
making a combined work based on this library.  Thus, the terms and
conditions of the GNU General Public License cover the whole
combination.

As a special exception, the copyright holders of this library give you
permission to link this library with independent modules to produce an
executable, regardless of the license terms of these independent
modules, and to copy and distribute the resulting executable under
terms of your choice, provided that you also meet, for each linked
independent module, the terms and conditions of the license of that
module.  An independent module is a module which is not derived from
or based on this library.  If you modify this library, you may extend
this exception to your version of the library, but you are not
obligated to do so.  If you do not wish to do so, delete this
exception statement from your version. */


package java.lang;

import gnu.java.lang.CharData;

import java.io.Serializable;

/**
 * Wrapper class for the primitive char data type.  In addition, this class
 * allows one to retrieve property information and perform transformations
 * on the 57,707 defined characters in the Unicode Standard, Version 3.0.0.
 * java.lang.Character is designed to be very dynamic, and as such, it
 * retrieves information on the Unicode character set from a separate
 * database, gnu.java.lang.CharData, which can be easily upgraded.
 *
 * <p>For predicates, boundaries are used to describe
 * the set of characters for which the method will return true.
 * This syntax uses fairly normal regular expression notation.
 * See 5.13 of the Unicode Standard, Version 3.0, for the
 * boundary specification.
 *
 * <p>See <a href="http://www.unicode.org">http://www.unicode.org</a>
 * for more information on the Unicode Standard.
 *
 * @author Tom Tromey (tromey@cygnus.com)
 * @author Paul N. Fisher
 * @author Jochen Hoenicke
 * @author Eric Blake (ebb9@email.byu.edu)
 * @see CharData
 * @since 1.0
 * @status updated to 1.4
 */
public final class Character implements Serializable, Comparable
{
  /**
   * A subset of Unicode blocks.
   *
   * @author Paul N. Fisher
   * @author Eric Blake (ebb9@email.byu.edu)
   * @since 1.2
   */
  public static class Subset
  {
    /** The name of the subset. */
    private final String name;

    /**
     * Construct a new subset of characters.
     *
     * @param name the name of the subset
     * @throws NullPointerException if name is null
     */
    protected Subset(String name)
    {
      // Note that name.toString() is name, unless name was null.
      this.name = name.toString();
    }

    /**
     * Compares two Subsets for equality. This is <code>final</code>, and
     * restricts the comparison on the <code>==</code> operator, so it returns
     * true only for the same object.
     *
     * @param o the object to compare
     * @return true if o is this
     */
    public final boolean equals(Object o)
    {
      return o == this;
    }

    /**
     * Makes the original hashCode of Object final, to be consistent with
     * equals.
     *
     * @return the hash code for this object
     */
    public final int hashCode()
    {
      return super.hashCode();
    }

    /**
     * Returns the name of the subset.
     *
     * @return the name
     */
    public final String toString()
    {
      return name;
    }
  } // class Subset

  /**
   * A family of character subsets in the Unicode specification. A character
   * is in at most one of these blocks.
   *
   * This inner class was generated automatically from
   * <code>doc/unicode/Block-3.txt</code>, by some perl scripts.
   * This Unicode definition file can be found on the
   * <a href="http://www.unicode.org">http://www.unicode.org</a> website.
   * JDK 1.4 uses Unicode version 3.0.0.
   *
   * @author scripts/unicode-blocks.pl (written by Eric Blake)
   * @since 1.2
   */
  public static final class UnicodeBlock extends Subset
  {
    /** The start of the subset. */
    private final char start;

    /** The end of the subset. */
    private final char end;

    /**
     * Constructor for strictly defined blocks.
     *
     * @param start the start character of the range
     * @param end the end character of the range
     * @param name the block name
     */
    private UnicodeBlock(char start, char end, String name)
    {
      super(name);
      this.start = start;
      this.end = end;
    }

    /**
     * Returns the Unicode character block which a character belongs to.
     *
     * @param ch the character to look up
     * @return the set it belongs to, or null if it is not in one
     */
    public static UnicodeBlock of(char ch)
    {
      // Special case, since SPECIALS contains two ranges.
      if (ch == 'u')
        return SPECIALS;
      // Simple binary search for the correct block.
      int low = 0;
      int hi = sets.length - 1;
      while (low <= hi)
        {
          int mid = (low + hi) >> 1;
          UnicodeBlock b = sets[mid];
          if (ch < b.start)
            hi = mid - 1;
          else if (ch > b.end)
            low = mid + 1;
          else
            return b;
        }
      return null;
    }

    /**
     * Basic Latin.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock BASIC_LATIN
      = new UnicodeBlock('u', 'u',
                         "BASIC_LATIN");

    /**
     * Latin-1 Supplement.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock LATIN_1_SUPPLEMENT
      = new UnicodeBlock('u', 'u',
                         "LATIN_1_SUPPLEMENT");

    /**
     * Latin Extended-A.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock LATIN_EXTENDED_A
      = new UnicodeBlock('u', 'u',
                         "LATIN_EXTENDED_A");

    /**
     * Latin Extended-B.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock LATIN_EXTENDED_B
      = new UnicodeBlock('u', 'u',
                         "LATIN_EXTENDED_B");

    /**
     * IPA Extensions.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock IPA_EXTENSIONS
      = new UnicodeBlock('u', 'u',
                         "IPA_EXTENSIONS");

    /**
     * Spacing Modifier Letters.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock SPACING_MODIFIER_LETTERS
      = new UnicodeBlock('u', 'u',
                         "SPACING_MODIFIER_LETTERS");

    /**
     * Combining Diacritical Marks.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock COMBINING_DIACRITICAL_MARKS
      = new UnicodeBlock('u', 'u',
                         "COMBINING_DIACRITICAL_MARKS");

    /**
     * Greek.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock GREEK
      = new UnicodeBlock('u', 'u',
                         "GREEK");

    /**
     * Cyrillic.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock CYRILLIC
      = new UnicodeBlock('u', 'u',
                         "CYRILLIC");

    /**
     * Armenian.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock ARMENIAN
      = new UnicodeBlock('u', 'u',
                         "ARMENIAN");

    /**
     * Hebrew.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock HEBREW
      = new UnicodeBlock('u', 'u',
                         "HEBREW");

    /**
     * Arabic.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock ARABIC
      = new UnicodeBlock('u', 'u',
                         "ARABIC");

    /**
     * Syriac.
     * 'u' - 'u'.
     * @since 1.4
     */
    public static final UnicodeBlock SYRIAC
      = new UnicodeBlock('u', 'u',
                         "SYRIAC");

    /**
     * Thaana.
     * 'u' - 'u'.
     * @since 1.4
     */
    public static final UnicodeBlock THAANA
      = new UnicodeBlock('u', 'u',
                         "THAANA");

    /**
     * Devanagari.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock DEVANAGARI
      = new UnicodeBlock('u', 'u',
                         "DEVANAGARI");

    /**
     * Bengali.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock BENGALI
      = new UnicodeBlock('u', 'u',
                         "BENGALI");

    /**
     * Gurmukhi.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock GURMUKHI
      = new UnicodeBlock('u', 'u',
                         "GURMUKHI");

    /**
     * Gujarati.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock GUJARATI
      = new UnicodeBlock('u', 'u',
                         "GUJARATI");

    /**
     * Oriya.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock ORIYA
      = new UnicodeBlock('u', 'u',
                         "ORIYA");

    /**
     * Tamil.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock TAMIL
      = new UnicodeBlock('u', 'u',
                         "TAMIL");

    /**
     * Telugu.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock TELUGU
      = new UnicodeBlock('u', 'u',
                         "TELUGU");

    /**
     * Kannada.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock KANNADA
      = new UnicodeBlock('u', 'u',
                         "KANNADA");

    /**
     * Malayalam.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock MALAYALAM
      = new UnicodeBlock('u', 'u',
                         "MALAYALAM");

    /**
     * Sinhala.
     * 'u' - 'u'.
     * @since 1.4
     */
    public static final UnicodeBlock SINHALA
      = new UnicodeBlock('u', 'u',
                         "SINHALA");

    /**
     * Thai.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock THAI
      = new UnicodeBlock('u', 'u',
                         "THAI");

    /**
     * Lao.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock LAO
      = new UnicodeBlock('u', 'u',
                         "LAO");

    /**
     * Tibetan.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock TIBETAN
      = new UnicodeBlock('u', 'u',
                         "TIBETAN");

    /**
     * Myanmar.
     * 'u' - 'u'.
     * @since 1.4
     */
    public static final UnicodeBlock MYANMAR
      = new UnicodeBlock('u', 'u',
                         "MYANMAR");

    /**
     * Georgian.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock GEORGIAN
      = new UnicodeBlock('u', 'u',
                         "GEORGIAN");

    /**
     * Hangul Jamo.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock HANGUL_JAMO
      = new UnicodeBlock('u', 'u',
                         "HANGUL_JAMO");

    /**
     * Ethiopic.
     * 'u' - 'u'.
     * @since 1.4
     */
    public static final UnicodeBlock ETHIOPIC
      = new UnicodeBlock('u', 'u',
                         "ETHIOPIC");

    /**
     * Cherokee.
     * 'u' - 'u'.
     * @since 1.4
     */
    public static final UnicodeBlock CHEROKEE
      = new UnicodeBlock('u', 'u',
                         "CHEROKEE");

    /**
     * Unified Canadian Aboriginal Syllabics.
     * 'u' - 'u'.
     * @since 1.4
     */
    public static final UnicodeBlock UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS
      = new UnicodeBlock('u', 'u',
                         "UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS");

    /**
     * Ogham.
     * 'u' - 'u'.
     * @since 1.4
     */
    public static final UnicodeBlock OGHAM
      = new UnicodeBlock('u', 'u',
                         "OGHAM");

    /**
     * Runic.
     * 'u' - 'u'.
     * @since 1.4
     */
    public static final UnicodeBlock RUNIC
      = new UnicodeBlock('u', 'u',
                         "RUNIC");

    /**
     * Khmer.
     * 'u' - 'u'.
     * @since 1.4
     */
    public static final UnicodeBlock KHMER
      = new UnicodeBlock('u', 'u',
                         "KHMER");

    /**
     * Mongolian.
     * 'u' - 'u'.
     * @since 1.4
     */
    public static final UnicodeBlock MONGOLIAN
      = new UnicodeBlock('u', 'u',
                         "MONGOLIAN");

    /**
     * Latin Extended Additional.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock LATIN_EXTENDED_ADDITIONAL
      = new UnicodeBlock('u', 'u',
                         "LATIN_EXTENDED_ADDITIONAL");

    /**
     * Greek Extended.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock GREEK_EXTENDED
      = new UnicodeBlock('u', 'u',
                         "GREEK_EXTENDED");

    /**
     * General Punctuation.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock GENERAL_PUNCTUATION
      = new UnicodeBlock('u', 'u',
                         "GENERAL_PUNCTUATION");

    /**
     * Superscripts and Subscripts.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock SUPERSCRIPTS_AND_SUBSCRIPTS
      = new UnicodeBlock('u', 'u',
                         "SUPERSCRIPTS_AND_SUBSCRIPTS");

    /**
     * Currency Symbols.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock CURRENCY_SYMBOLS
      = new UnicodeBlock('u', 'u',
                         "CURRENCY_SYMBOLS");

    /**
     * Combining Marks for Symbols.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock COMBINING_MARKS_FOR_SYMBOLS
      = new UnicodeBlock('u', 'u',
                         "COMBINING_MARKS_FOR_SYMBOLS");

    /**
     * Letterlike Symbols.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock LETTERLIKE_SYMBOLS
      = new UnicodeBlock('u', 'u',
                         "LETTERLIKE_SYMBOLS");

    /**
     * Number Forms.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock NUMBER_FORMS
      = new UnicodeBlock('u', 'u',
                         "NUMBER_FORMS");

    /**
     * Arrows.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock ARROWS
      = new UnicodeBlock('u', 'u',
                         "ARROWS");

    /**
     * Mathematical Operators.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock MATHEMATICAL_OPERATORS
      = new UnicodeBlock('u', 'u',
                         "MATHEMATICAL_OPERATORS");

    /**
     * Miscellaneous Technical.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock MISCELLANEOUS_TECHNICAL
      = new UnicodeBlock('u', 'u',
                         "MISCELLANEOUS_TECHNICAL");

    /**
     * Control Pictures.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock CONTROL_PICTURES
      = new UnicodeBlock('u', 'u',
                         "CONTROL_PICTURES");

    /**
     * Optical Character Recognition.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock OPTICAL_CHARACTER_RECOGNITION
      = new UnicodeBlock('u', 'u',
                         "OPTICAL_CHARACTER_RECOGNITION");

    /**
     * Enclosed Alphanumerics.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock ENCLOSED_ALPHANUMERICS
      = new UnicodeBlock('u', 'u',
                         "ENCLOSED_ALPHANUMERICS");

    /**
     * Box Drawing.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock BOX_DRAWING
      = new UnicodeBlock('u', 'u',
                         "BOX_DRAWING");

    /**
     * Block Elements.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock BLOCK_ELEMENTS
      = new UnicodeBlock('u', 'u',
                         "BLOCK_ELEMENTS");

    /**
     * Geometric Shapes.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock GEOMETRIC_SHAPES
      = new UnicodeBlock('u', 'u',
                         "GEOMETRIC_SHAPES");

    /**
     * Miscellaneous Symbols.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock MISCELLANEOUS_SYMBOLS
      = new UnicodeBlock('u', 'u',
                         "MISCELLANEOUS_SYMBOLS");

    /**
     * Dingbats.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock DINGBATS
      = new UnicodeBlock('u', 'u',
                         "DINGBATS");

    /**
     * Braille Patterns.
     * 'u' - 'u'.
     * @since 1.4
     */
    public static final UnicodeBlock BRAILLE_PATTERNS
      = new UnicodeBlock('u', 'u',
                         "BRAILLE_PATTERNS");

    /**
     * CJK Radicals Supplement.
     * 'u' - 'u'.
     * @since 1.4
     */
    public static final UnicodeBlock CJK_RADICALS_SUPPLEMENT
      = new UnicodeBlock('u', 'u',
                         "CJK_RADICALS_SUPPLEMENT");

    /**
     * Kangxi Radicals.
     * 'u' - 'u'.
     * @since 1.4
     */
    public static final UnicodeBlock KANGXI_RADICALS
      = new UnicodeBlock('u', 'u',
                         "KANGXI_RADICALS");

    /**
     * Ideographic Description Characters.
     * 'u' - 'u'.
     * @since 1.4
     */
    public static final UnicodeBlock IDEOGRAPHIC_DESCRIPTION_CHARACTERS
      = new UnicodeBlock('u', 'u',
                         "IDEOGRAPHIC_DESCRIPTION_CHARACTERS");

    /**
     * CJK Symbols and Punctuation.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock CJK_SYMBOLS_AND_PUNCTUATION
      = new UnicodeBlock('u', 'u',
                         "CJK_SYMBOLS_AND_PUNCTUATION");

    /**
     * Hiragana.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock HIRAGANA
      = new UnicodeBlock('u', 'u',
                         "HIRAGANA");

    /**
     * Katakana.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock KATAKANA
      = new UnicodeBlock('u', 'u',
                         "KATAKANA");

    /**
     * Bopomofo.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock BOPOMOFO
      = new UnicodeBlock('u', 'u',
                         "BOPOMOFO");

    /**
     * Hangul Compatibility Jamo.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock HANGUL_COMPATIBILITY_JAMO
      = new UnicodeBlock('u', 'u',
                         "HANGUL_COMPATIBILITY_JAMO");

    /**
     * Kanbun.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock KANBUN
      = new UnicodeBlock('u', 'u',
                         "KANBUN");

    /**
     * Bopomofo Extended.
     * 'u' - 'u'.
     * @since 1.4
     */
    public static final UnicodeBlock BOPOMOFO_EXTENDED
      = new UnicodeBlock('u', 'u',
                         "BOPOMOFO_EXTENDED");

    /**
     * Enclosed CJK Letters and Months.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock ENCLOSED_CJK_LETTERS_AND_MONTHS
      = new UnicodeBlock('u', 'u',
                         "ENCLOSED_CJK_LETTERS_AND_MONTHS");

    /**
     * CJK Compatibility.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock CJK_COMPATIBILITY
      = new UnicodeBlock('u', 'u',
                         "CJK_COMPATIBILITY");

    /**
     * CJK Unified Ideographs Extension A.
     * 'u' - 'u'.
     * @since 1.4
     */
    public static final UnicodeBlock CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
      = new UnicodeBlock('u', 'u',
                         "CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A");

    /**
     * CJK Unified Ideographs.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock CJK_UNIFIED_IDEOGRAPHS
      = new UnicodeBlock('u', 'u',
                         "CJK_UNIFIED_IDEOGRAPHS");

    /**
     * Yi Syllables.
     * 'u' - 'u'.
     * @since 1.4
     */
    public static final UnicodeBlock YI_SYLLABLES
      = new UnicodeBlock('u', 'u',
                         "YI_SYLLABLES");

    /**
     * Yi Radicals.
     * 'u' - 'u'.
     * @since 1.4
     */
    public static final UnicodeBlock YI_RADICALS
      = new UnicodeBlock('u', 'u',
                         "YI_RADICALS");

    /**
     * Hangul Syllables.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock HANGUL_SYLLABLES
      = new UnicodeBlock('u', 'u',
                         "HANGUL_SYLLABLES");

    /**
     * Surrogates Area.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock SURROGATES_AREA
      = new UnicodeBlock('u', 'u',
                         "SURROGATES_AREA");

    /**
     * Private Use Area.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock PRIVATE_USE_AREA
      = new UnicodeBlock('u', 'u',
                         "PRIVATE_USE_AREA");

    /**
     * CJK Compatibility Ideographs.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock CJK_COMPATIBILITY_IDEOGRAPHS
      = new UnicodeBlock('u', 'u',
                         "CJK_COMPATIBILITY_IDEOGRAPHS");

    /**
     * Alphabetic Presentation Forms.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock ALPHABETIC_PRESENTATION_FORMS
      = new UnicodeBlock('u', 'u',
                         "ALPHABETIC_PRESENTATION_FORMS");

    /**
     * Arabic Presentation Forms-A.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock ARABIC_PRESENTATION_FORMS_A
      = new UnicodeBlock('u', 'u',
                         "ARABIC_PRESENTATION_FORMS_A");

    /**
     * Combining Half Marks.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock COMBINING_HALF_MARKS
      = new UnicodeBlock('u', 'u',
                         "COMBINING_HALF_MARKS");

    /**
     * CJK Compatibility Forms.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock CJK_COMPATIBILITY_FORMS
      = new UnicodeBlock('u', 'u',
                         "CJK_COMPATIBILITY_FORMS");

    /**
     * Small Form Variants.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock SMALL_FORM_VARIANTS
      = new UnicodeBlock('u', 'u',
                         "SMALL_FORM_VARIANTS");

    /**
     * Arabic Presentation Forms-B.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock ARABIC_PRESENTATION_FORMS_B
      = new UnicodeBlock('u', 'u',
                         "ARABIC_PRESENTATION_FORMS_B");

    /**
     * Halfwidth and Fullwidth Forms.
     * 'u' - 'u'.
     */
    public static final UnicodeBlock HALFWIDTH_AND_FULLWIDTH_FORMS
      = new UnicodeBlock('u', 'u',
                         "HALFWIDTH_AND_FULLWIDTH_FORMS");

    /**
     * Specials.
     * 'u', 'u' - 'u'.
     */
    public static final UnicodeBlock SPECIALS
      = new UnicodeBlock('u', 'u',
                         "SPECIALS");

    /**
     * The defined subsets.
     */
    private static final UnicodeBlock sets[] = {
      BASIC_LATIN,
      LATIN_1_SUPPLEMENT,
      LATIN_EXTENDED_A,
      LATIN_EXTENDED_B,
      IPA_EXTENSIONS,
      SPACING_MODIFIER_LETTERS,
      COMBINING_DIACRITICAL_MARKS,
      GREEK,
      CYRILLIC,
      ARMENIAN,
      HEBREW,
      ARABIC,
      SYRIAC,
      THAANA,
      DEVANAGARI,
      BENGALI,
      GURMUKHI,
      GUJARATI,
      ORIYA,
      TAMIL,
      TELUGU,
      KANNADA,
      MALAYALAM,
      SINHALA,
      THAI,
      LAO,
      TIBETAN,
      MYANMAR,
      GEORGIAN,
      HANGUL_JAMO,
      ETHIOPIC,
      CHEROKEE,
      UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS,
      OGHAM,
      RUNIC,
      KHMER,
      MONGOLIAN,
      LATIN_EXTENDED_ADDITIONAL,
      GREEK_EXTENDED,
      GENERAL_PUNCTUATION,
      SUPERSCRIPTS_AND_SUBSCRIPTS,
      CURRENCY_SYMBOLS,
      COMBINING_MARKS_FOR_SYMBOLS,
      LETTERLIKE_SYMBOLS,
      NUMBER_FORMS,
      ARROWS,
      MATHEMATICAL_OPERATORS,
      MISCELLANEOUS_TECHNICAL,
      CONTROL_PICTURES,
      OPTICAL_CHARACTER_RECOGNITION,
      ENCLOSED_ALPHANUMERICS,
      BOX_DRAWING,
      BLOCK_ELEMENTS,
      GEOMETRIC_SHAPES,
      MISCELLANEOUS_SYMBOLS,
      DINGBATS,
      BRAILLE_PATTERNS,
      CJK_RADICALS_SUPPLEMENT,
      KANGXI_RADICALS,
      IDEOGRAPHIC_DESCRIPTION_CHARACTERS,
      CJK_SYMBOLS_AND_PUNCTUATION,
      HIRAGANA,
      KATAKANA,
      BOPOMOFO,
      HANGUL_COMPATIBILITY_JAMO,
      KANBUN,
      BOPOMOFO_EXTENDED,
      ENCLOSED_CJK_LETTERS_AND_MONTHS,
      CJK_COMPATIBILITY,
      CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A,
      CJK_UNIFIED_IDEOGRAPHS,
      YI_SYLLABLES,
      YI_RADICALS,
      HANGUL_SYLLABLES,
      SURROGATES_AREA,
      PRIVATE_USE_AREA,
      CJK_COMPATIBILITY_IDEOGRAPHS,
      ALPHABETIC_PRESENTATION_FORMS,
      ARABIC_PRESENTATION_FORMS_A,
      COMBINING_HALF_MARKS,
      CJK_COMPATIBILITY_FORMS,
      SMALL_FORM_VARIANTS,
      ARABIC_PRESENTATION_FORMS_B,
      HALFWIDTH_AND_FULLWIDTH_FORMS,
      SPECIALS,
    };
  } // class UnicodeBlock

  /**
   * The immutable value of this Character.
   *
   * @serial the value of this Character
   */
  private final char value;

  /**
   * Compatible with JDK 1.0+.
   */
  private static final long serialVersionUID = 3786198910865385080L;

  /**
   * Smallest value allowed for radix arguments in Java. This value is 2.
   *
   * @see #digit(char, int)
   * @see #forDigit(int, int)
   * @see Integer#toString(int, int)
   * @see Integer#valueOf(String)
   */
  public static final int MIN_RADIX = 2;

  /**
   * Largest value allowed for radix arguments in Java. This value is 36.
   *
   * @see #digit(char, int)
   * @see #forDigit(int, int)
   * @see Integer#toString(int, int)
   * @see Integer#valueOf(String)
   */
  public static final int MAX_RADIX = 36;

  /**
   * The minimum value the char data type can hold.
   * This value is <code>'\u'</code>.
   */
  public static final char MIN_VALUE = 'u';

  /**
   * The maximum value the char data type can hold.
   * This value is <code>'\u'</code>.
   */
  public static final char MAX_VALUE = 'u';

  /**
   * Class object representing the primitive char data type.
   *
   * @since 1.1
   */
  public static final Class TYPE = VMClassLoader.getPrimitiveClass('C');

  /**
   * Lu = Letter, Uppercase (Informative).
   *
   * @since 1.1
   */
  public static final byte UPPERCASE_LETTER = 1;

  /**
   * Ll = Letter, Lowercase (Informative).
   *
   * @since 1.1
   */
  public static final byte LOWERCASE_LETTER = 2;

  /**
   * Lt = Letter, Titlecase (Informative).
   *
   * @since 1.1
   */
  public static final byte TITLECASE_LETTER = 3;

  /**
   * Mn = Mark, Non-Spacing (Normative).
   *
   * @since 1.1
   */
  public static final byte NON_SPACING_MARK = 6;

  /**
   * Mc = Mark, Spacing Combining (Normative).
   *
   * @since 1.1
   */
  public static final byte COMBINING_SPACING_MARK = 8;

  /**
   * Me = Mark, Enclosing (Normative).
   *
   * @since 1.1
   */
  public static final byte ENCLOSING_MARK = 7;

  /**
   * Nd = Number, Decimal Digit (Normative).
   *
   * @since 1.1
   */
  public static final byte DECIMAL_DIGIT_NUMBER = 9;

  /**
   * Nl = Number, Letter (Normative).
   *
   * @since 1.1
   */
  public static final byte LETTER_NUMBER = 10;

  /**
   * No = Number, Other (Normative).
   *
   * @since 1.1
   */
  public static final byte OTHER_NUMBER = 11;

  /**
   * Zs = Separator, Space (Normative).
   *
   * @since 1.1
   */
  public static final byte SPACE_SEPARATOR = 12;

  /**
   * Zl = Separator, Line (Normative).
   *
   * @since 1.1
   */
  public static final byte LINE_SEPARATOR = 13;

  /**
   * Zp = Separator, Paragraph (Normative).
   *
   * @since 1.1
   */
  public static final byte PARAGRAPH_SEPARATOR = 14;

  /**
   * Cc = Other, Control (Normative).
   *
   * @since 1.1
   */
  public static final byte CONTROL = 15;

  /**
   * Cf = Other, Format (Normative).
   *
   * @since 1.1
   */
  public static final byte FORMAT = 16;

  /**
   * Cs = Other, Surrogate (Normative).
   *
   * @since 1.1
   */
  public static final byte SURROGATE = 19;

  /**
   * Co = Other, Private Use (Normative).
   *
   * @since 1.1
   */
  public static final byte PRIVATE_USE = 18;

  /**
   * Cn = Other, Not Assigned (Normative).
   *
   * @since 1.1
   */
  public static final byte UNASSIGNED = 0;

  /**
   * Lm = Letter, Modifier (Informative).
   *
   * @since 1.1
   */
  public static final byte MODIFIER_LETTER = 4;

  /**
   * Lo = Letter, Other (Informative).
   *
   * @since 1.1
   */
  public static final byte OTHER_LETTER = 5;

  /**
   * Pc = Punctuation, Connector (Informative).
   *
   * @since 1.1
   */
  public static final byte CONNECTOR_PUNCTUATION = 23;

  /**
   * Pd = Punctuation, Dash (Informative).
   *
   * @since 1.1
   */
  public static final byte DASH_PUNCTUATION = 20;

  /**
   * Ps = Punctuation, Open (Informative).
   *
   * @since 1.1
   */
  public static final byte START_PUNCTUATION = 21;

  /**
   * Pe = Punctuation, Close (Informative).
   *
   * @since 1.1
   */
  public static final byte END_PUNCTUATION = 22;

  /**
   * Pi = Punctuation, Initial Quote (Informative).
   *
   * @since 1.4
   */
  public static final byte INITIAL_QUOTE_PUNCTUATION = 29;

  /**
   * Pf = Punctuation, Final Quote (Informative).
   *
   * @since 1.4
   */
  public static final byte FINAL_QUOTE_PUNCTUATION = 30;

  /**
   * Po = Punctuation, Other (Informative).
   *
   * @since 1.1
   */
  public static final byte OTHER_PUNCTUATION = 24;

  /**
   * Sm = Symbol, Math (Informative).
   *
   * @since 1.1
   */
  public static final byte MATH_SYMBOL = 25;

  /**
   * Sc = Symbol, Currency (Informative).
   *
   * @since 1.1
   */
  public static final byte CURRENCY_SYMBOL = 26;

  /**
   * Sk = Symbol, Modifier (Informative).
   *
   * @since 1.1
   */
  public static final byte MODIFIER_SYMBOL = 27;

  /**
   * So = Symbol, Other (Informative).
   *
   * @since 1.1
   */
  public static final byte OTHER_SYMBOL = 28;

  /**
   * Undefined bidirectional character type. Undefined char values have
   * undefined directionality in the Unicode specification.
   *
   * @since 1.4
   */
  public static final byte DIRECTIONALITY_UNDEFINED = -1;

  /**
   * Strong bidirectional character type "L".
   *
   * @since 1.4
   */
  public static final byte DIRECTIONALITY_LEFT_TO_RIGHT = 0;

  /**
   * Strong bidirectional character type "R".
   *
   * @since 1.4
   */
  public static final byte DIRECTIONALITY_RIGHT_TO_LEFT = 1;

  /**
   * Strong bidirectional character type "AL".
   *
   * @since 1.4
   */
  public static final byte DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC = 2;

  /**
   * Weak bidirectional character type "EN".
   *
   * @since 1.4
   */
  public static final byte DIRECTIONALITY_EUROPEAN_NUMBER = 3;

  /**
   * Weak bidirectional character type "ES".
   *
   * @since 1.4
   */
  public static final byte DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR = 4;

  /**
   * Weak bidirectional character type "ET".
   *
   * @since 1.4
   */
  public static final byte DIRECTIONALITY_EUROPEAN_NUMBER_TERMINATOR = 5;

  /**
   * Weak bidirectional character type "AN".
   *
   * @since 1.4
   */
  public static final byte DIRECTIONALITY_ARABIC_NUMBER = 6;

  /**
   * Weak bidirectional character type "CS".
   *
   * @since 1.4
   */
  public static final byte DIRECTIONALITY_COMMON_NUMBER_SEPARATOR = 7;

  /**
   * Weak bidirectional character type "NSM".
   *
   * @since 1.4
   */
  public static final byte DIRECTIONALITY_NONSPACING_MARK = 8;

  /**
   * Weak bidirectional character type "BN".
   *
   * @since 1.4
   */
  public static final byte DIRECTIONALITY_BOUNDARY_NEUTRAL = 9;

  /**
   * Neutral bidirectional character type "B".
   *
   * @since 1.4
   */
  public static final byte DIRECTIONALITY_PARAGRAPH_SEPARATOR = 10;

  /**
   * Neutral bidirectional character type "S".
   *
   * @since 1.4
   */
  public static final byte DIRECTIONALITY_SEGMENT_SEPARATOR = 11;

  /**
   * Strong bidirectional character type "WS".
   *
   * @since 1.4
   */
  public static final byte DIRECTIONALITY_WHITESPACE = 12;

  /**
   * Neutral bidirectional character type "ON".
   *
   * @since 1.4
   */
  public static final byte DIRECTIONALITY_OTHER_NEUTRALS = 13;

  /**
   * Strong bidirectional character type "LRE".
   *
   * @since 1.4
   */
  public static final byte DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING = 14;

  /**
   * Strong bidirectional character type "LRO".
   *
   * @since 1.4
   */
  public static final byte DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE = 15;

  /**
   * Strong bidirectional character type "RLE".
   *
   * @since 1.4
   */
  public static final byte DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING = 16;

  /**
   * Strong bidirectional character type "RLO".
   *
   * @since 1.4
   */
  public static final byte DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE = 17;

  /**
   * Weak bidirectional character type "PDF".
   *
   * @since 1.4
   */
  public static final byte DIRECTIONALITY_POP_DIRECTIONAL_FORMAT = 18;

  /**
   * Stores unicode block offset lookup table. Exploit package visibility of
   * String.value to avoid copying the array.
   * @see #readChar(char)
   * @see CharData#BLOCKS
   */
  private static final char[] blocks = String.zeroBasedStringValue(CharData.BLOCKS);

  /**
   * Stores unicode attribute offset lookup table. Exploit package visibility
   * of String.value to avoid copying the array.
   * @see CharData#DATA
   */
  private static final char[] data = String.zeroBasedStringValue(CharData.DATA);

  /**
   * Stores unicode numeric value attribute table. Exploit package visibility
   * of String.value to avoid copying the array.
   * @see CharData#NUM_VALUE
   */
  private static final char[] numValue
	  = String.zeroBasedStringValue(CharData.NUM_VALUE);

  /**
   * Stores unicode uppercase attribute table. Exploit package visibility
   * of String.value to avoid copying the array.
   * @see CharData#UPPER
   */
  private static final char[] upper = String.zeroBasedStringValue(CharData.UPPER);

  /**
   * Stores unicode lowercase attribute table. Exploit package visibility
   * of String.value to avoid copying the array.
   * @see CharData#LOWER
   */
  private static final char[] lower = String.zeroBasedStringValue(CharData.LOWER);

  /**
   * Stores unicode direction attribute table. Exploit package visibility
   * of String.value to avoid copying the array.
   * @see CharData#DIRECTION
   */
  // Package visible for use by String.
  static final char[] direction = String.zeroBasedStringValue(CharData.DIRECTION);

  /**
   * Stores unicode titlecase table. Exploit package visibility of
   * String.value to avoid copying the array.
   * @see CharData#TITLE
   */
  private static final char[] title = String.zeroBasedStringValue(CharData.TITLE);

  /**
   * Mask for grabbing the type out of the contents of data.
   * @see CharData#DATA
   */
  private static final int TYPE_MASK = 0x1F;

  /**
   * Mask for grabbing the non-breaking space flag out of the contents of
   * data.
   * @see CharData#DATA
   */
  private static final int NO_BREAK_MASK = 0x20;

  /**
   * Mask for grabbing the mirrored directionality flag out of the contents
   * of data.
   * @see CharData#DATA
   */
  private static final int MIRROR_MASK = 0x40;

  /**
   * Grabs an attribute offset from the Unicode attribute database. The lower
   * 5 bits are the character type, the next 2 bits are flags, and the top
   * 9 bits are the offset into the attribute tables.
   *
   * @param ch the character to look up
   * @return the character's attribute offset and type
   * @see #TYPE_MASK
   * @see #NO_BREAK_MASK
   * @see #MIRROR_MASK
   * @see CharData#DATA
   * @see CharData#SHIFT
   */
  // Package visible for use in String.
  static char readChar(char ch)
  {
    // Perform 16-bit addition to find the correct entry in data.
    return data[(char) (blocks[ch >> CharData.SHIFT] + ch)];
  }

  /**
   * Wraps up a character.
   *
   * @param value the character to wrap
   */
  public Character(char value)
  {
    this.value = value;
  }

  /**
   * Returns the character which has been wrapped by this class.
   *
   * @return the character wrapped
   */
  public char charValue()
  {
    return value;
  }

  /**
   * Returns the numerical value (unsigned) of the wrapped character.
   * Range of returned values: 0x0000-0xFFFF.
   *
   * @return the value of the wrapped character
   */
  public int hashCode()
  {
    return value;
  }

  /**
   * Determines if an object is equal to this object. This is only true for
   * another Character object wrapping the same value.
   *
   * @param o object to compare
   * @return true if o is a Character with the same value
   */
  public boolean equals(Object o)
  {
    return o instanceof Character && value == ((Character) o).value;
  }

  /**
   * Converts the wrapped character into a String.
   *
   * @return a String containing one character -- the wrapped character
   *         of this instance
   */
  public String toString()
  {
    // Package constructor avoids an array copy.
    return new String(new char[] { value }, 0, 1, true);
  }

  /**
   * Returns a String of length 1 representing the specified character.
   *
   * @param ch the character to convert
   * @return a String containing the character
   * @since 1.4
   */
  public static String toString(char ch)
  {
    // Package constructor avoids an array copy.
    return new String(new char[] { ch }, 0, 1, true);
  }

  /**
   * Determines if a character is a Unicode lowercase letter. For example,
   * <code>'a'</code> is lowercase.
   * <br>
   * lowercase = [Ll]
   *
   * @param ch character to test
   * @return true if ch is a Unicode lowercase letter, else false
   * @see #isUpperCase(char)
   * @see #isTitleCase(char)
   * @see #toLowerCase(char)
   * @see #getType(char)
   */
  public static boolean isLowerCase(char ch)
  {
    return getType(ch) == LOWERCASE_LETTER;
  }

  /**
   * Determines if a character is a Unicode uppercase letter. For example,
   * <code>'A'</code> is uppercase.
   * <br>
   * uppercase = [Lu]
   *
   * @param ch character to test
   * @return true if ch is a Unicode uppercase letter, else false
   * @see #isLowerCase(char)
   * @see #isTitleCase(char)
   * @see #toUpperCase(char)
   * @see #getType(char)
   */
  public static boolean isUpperCase(char ch)
  {
    return getType(ch) == UPPERCASE_LETTER;
  }

  /**
   * Determines if a character is a Unicode titlecase letter. For example,
   * the character "Lj" (Latin capital L with small letter j) is titlecase.
   * <br>
   * titlecase = [Lt]
   *
   * @param ch character to test
   * @return true if ch is a Unicode titlecase letter, else false
   * @see #isLowerCase(char)
   * @see #isUpperCase(char)
   * @see #toTitleCase(char)
   * @see #getType(char)
   */
  public static boolean isTitleCase(char ch)
  {
    return getType(ch) == TITLECASE_LETTER;
  }

  /**
   * Determines if a character is a Unicode decimal digit. For example,
   * <code>'0'</code> is a digit.
   * <br>
   * Unicode decimal digit = [Nd]
   *
   * @param ch character to test
   * @return true if ch is a Unicode decimal digit, else false
   * @see #digit(char, int)
   * @see #forDigit(int, int)
   * @see #getType(char)
   */
  public static boolean isDigit(char ch)
  {
    return getType(ch) == DECIMAL_DIGIT_NUMBER;
  }

  /**
   * Determines if a character is part of the Unicode Standard. This is an
   * evolving standard, but covers every character in the data file.
   * <br>
   * defined = not [Cn]
   *
   * @param ch character to test
   * @return true if ch is a Unicode character, else false
   * @see #isDigit(char)
   * @see #isLetter(char)
   * @see #isLetterOrDigit(char)
   * @see #isLowerCase(char)
   * @see #isTitleCase(char)
   * @see #isUpperCase(char)
   */
  public static boolean isDefined(char ch)
  {
    return getType(ch) != UNASSIGNED;
  }

  /**
   * Determines if a character is a Unicode letter. Not all letters have case,
   * so this may return true when isLowerCase and isUpperCase return false.
   * <br>
   * letter = [Lu]|[Ll]|[Lt]|[Lm]|[Lo]
   *
   * @param ch character to test
   * @return true if ch is a Unicode letter, else false
   * @see #isDigit(char)
   * @see #isJavaIdentifierStart(char)
   * @see #isJavaLetter(char)
   * @see #isJavaLetterOrDigit(char)
   * @see #isLetterOrDigit(char)
   * @see #isLowerCase(char)
   * @see #isTitleCase(char)
   * @see #isUnicodeIdentifierStart(char)
   * @see #isUpperCase(char)
   */
  public static boolean isLetter(char ch)
  {
    return ((1 << getType(ch))
            & ((1 << UPPERCASE_LETTER)
               | (1 << LOWERCASE_LETTER)
               | (1 << TITLECASE_LETTER)
               | (1 << MODIFIER_LETTER)
               | (1 << OTHER_LETTER))) != 0;
  }

  /**
   * Determines if a character is a Unicode letter or a Unicode digit. This
   * is the combination of isLetter and isDigit.
   * <br>
   * letter or digit = [Lu]|[Ll]|[Lt]|[Lm]|[Lo]|[Nd]
   *
   * @param ch character to test
   * @return true if ch is a Unicode letter or a Unicode digit, else false
   * @see #isDigit(char)
   * @see #isJavaIdentifierPart(char)
   * @see #isJavaLetter(char)
   * @see #isJavaLetterOrDigit(char)
   * @see #isLetter(char)
   * @see #isUnicodeIdentifierPart(char)
   */
  public static boolean isLetterOrDigit(char ch)
  {
    return ((1 << getType(ch))
            & ((1 << UPPERCASE_LETTER)
               | (1 << LOWERCASE_LETTER)
               | (1 << TITLECASE_LETTER)
               | (1 << MODIFIER_LETTER)
               | (1 << OTHER_LETTER)
               | (1 << DECIMAL_DIGIT_NUMBER))) != 0;
  }

  /**
   * Determines if a character can start a Java identifier. This is the
   * combination of isLetter, any character where getType returns
   * LETTER_NUMBER, currency symbols (like '$'), and connecting punctuation
   * (like '_').
   *
   * @param ch character to test
   * @return true if ch can start a Java identifier, else false
   * @deprecated Replaced by {@link #isJavaIdentifierStart(char)}
   * @see #isJavaLetterOrDigit(char)
   * @see #isJavaIdentifierStart(char)
   * @see #isJavaIdentifierPart(char)
   * @see #isLetter(char)
   * @see #isLetterOrDigit(char)
   * @see #isUnicodeIdentifierStart(char)
   */
  public static boolean isJavaLetter(char ch)
  {
    return isJavaIdentifierStart(ch);
  }

  /**
   * Determines if a character can follow the first letter in
   * a Java identifier.  This is the combination of isJavaLetter (isLetter,
   * type of LETTER_NUMBER, currency, connecting punctuation) and digit,
   * numeric letter (like Roman numerals), combining marks, non-spacing marks,
   * or isIdentifierIgnorable.
   *
   * @param ch character to test
   * @return true if ch can follow the first letter in a Java identifier
   * @deprecated Replaced by {@link #isJavaIdentifierPart(char)}
   * @see #isJavaLetter(char)
   * @see #isJavaIdentifierStart(char)
   * @see #isJavaIdentifierPart(char)
   * @see #isLetter(char)
   * @see #isLetterOrDigit(char)
   * @see #isUnicodeIdentifierPart(char)
   * @see #isIdentifierIgnorable(char)
   */
  public static boolean isJavaLetterOrDigit(char ch)
  {
    return isJavaIdentifierPart(ch);
  }

  /**
   * Determines if a character can start a Java identifier. This is the
   * combination of isLetter, any character where getType returns
   * LETTER_NUMBER, currency symbols (like '$'), and connecting punctuation
   * (like '_').
   * <br>
   * Java identifier start = [Lu]|[Ll]|[Lt]|[Lm]|[Lo]|[Nl]|[Sc]|[Pc]
   *
   * @param ch character to test
   * @return true if ch can start a Java identifier, else false
   * @see #isJavaIdentifierPart(char)
   * @see #isLetter(char)
   * @see #isUnicodeIdentifierStart(char)
   * @since 1.1
   */
  public static boolean isJavaIdentifierStart(char ch)
  {
    return ((1 << getType(ch))
            & ((1 << UPPERCASE_LETTER)
               | (1 << LOWERCASE_LETTER)
               | (1 << TITLECASE_LETTER)
               | (1 << MODIFIER_LETTER)
               | (1 << OTHER_LETTER)
               | (1 << LETTER_NUMBER)
               | (1 << CURRENCY_SYMBOL)
               | (1 << CONNECTOR_PUNCTUATION))) != 0;
  }

  /**
   * Determines if a character can follow the first letter in
   * a Java identifier.  This is the combination of isJavaLetter (isLetter,
   * type of LETTER_NUMBER, currency, connecting punctuation) and digit,
   * numeric letter (like Roman numerals), combining marks, non-spacing marks,
   * or isIdentifierIgnorable.
   * <br>
   * Java identifier extender =
   *   [Lu]|[Ll]|[Lt]|[Lm]|[Lo]|[Nl]|[Sc]|[Pc]|[Mn]|[Mc]|[Nd]|[Cf]
   *   |U+0000-U+0008|U+000E-U+001B|U+007F-U+009F
   *
   * @param ch character to test
   * @return true if ch can follow the first letter in a Java identifier
   * @see #isIdentifierIgnorable(char)
   * @see #isJavaIdentifierStart(char)
   * @see #isLetterOrDigit(char)
   * @see #isUnicodeIdentifierPart(char)
   * @since 1.1
   */
  public static boolean isJavaIdentifierPart(char ch)
  {
    int category = getType(ch);
    return ((1 << category)
            & ((1 << UPPERCASE_LETTER)
               | (1 << LOWERCASE_LETTER)
               | (1 << TITLECASE_LETTER)
               | (1 << MODIFIER_LETTER)
               | (1 << OTHER_LETTER)
               | (1 << NON_SPACING_MARK)
               | (1 << COMBINING_SPACING_MARK)
               | (1 << DECIMAL_DIGIT_NUMBER)
               | (1 << LETTER_NUMBER)
               | (1 << CURRENCY_SYMBOL)
               | (1 << CONNECTOR_PUNCTUATION)
               | (1 << FORMAT))) != 0
      || (category == CONTROL && isIdentifierIgnorable(ch));
  }

  /**
   * Determines if a character can start a Unicode identifier.  Only
   * letters can start a Unicode identifier, but this includes characters
   * in LETTER_NUMBER.
   * <br>
   * Unicode identifier start = [Lu]|[Ll]|[Lt]|[Lm]|[Lo]|[Nl]
   *
   * @param ch character to test
   * @return true if ch can start a Unicode identifier, else false
   * @see #isJavaIdentifierStart(char)
   * @see #isLetter(char)
   * @see #isUnicodeIdentifierPart(char)
   * @since 1.1
   */
  public static boolean isUnicodeIdentifierStart(char ch)
  {
    return ((1 << getType(ch))
            & ((1 << UPPERCASE_LETTER)
               | (1 << LOWERCASE_LETTER)
               | (1 << TITLECASE_LETTER)
               | (1 << MODIFIER_LETTER)
               | (1 << OTHER_LETTER)
               | (1 << LETTER_NUMBER))) != 0;
  }

  /**
   * Determines if a character can follow the first letter in
   * a Unicode identifier. This includes letters, connecting punctuation,
   * digits, numeric letters, combining marks, non-spacing marks, and
   * isIdentifierIgnorable.
   * <br>
   * Unicode identifier extender =
   *   [Lu]|[Ll]|[Lt]|[Lm]|[Lo]|[Nl]|[Mn]|[Mc]|[Nd]|[Pc]|[Cf]|
   *   |U+0000-U+0008|U+000E-U+001B|U+007F-U+009F
   *
   * @param ch character to test
   * @return true if ch can follow the first letter in a Unicode identifier
   * @see #isIdentifierIgnorable(char)
   * @see #isJavaIdentifierPart(char)
   * @see #isLetterOrDigit(char)
   * @see #isUnicodeIdentifierStart(char)
   * @since 1.1
   */
  public static boolean isUnicodeIdentifierPart(char ch)
  {
    int category = getType(ch);
    return ((1 << category)
            & ((1 << UPPERCASE_LETTER)
               | (1 << LOWERCASE_LETTER)
               | (1 << TITLECASE_LETTER)
               | (1 << MODIFIER_LETTER)
               | (1 << OTHER_LETTER)
               | (1 << NON_SPACING_MARK)
               | (1 << COMBINING_SPACING_MARK)
               | (1 << DECIMAL_DIGIT_NUMBER)
               | (1 << LETTER_NUMBER)
               | (1 << CONNECTOR_PUNCTUATION)
               | (1 << FORMAT))) != 0
      || (category == CONTROL && isIdentifierIgnorable(ch));
  }

  /**
   * Determines if a character is ignorable in a Unicode identifier. This
   * includes the non-whitespace ISO control characters (<code>'u'</code>
   * through <code>'u'</code>, <code>'u'</code> through
   * <code>'u'</code>, and <code>'u'</code> through
   * <code>'u'</code>), and FORMAT characters.
   * <br>
   * Unicode identifier ignorable = [Cf]|U+0000-U+0008|U+000E-U+001B
   *    |U+007F-U+009F
   *
   * @param ch character to test
   * @return true if ch is ignorable in a Unicode or Java identifier
   * @see #isJavaIdentifierPart(char)
   * @see #isUnicodeIdentifierPart(char)
   * @since 1.1
   */
  public static boolean isIdentifierIgnorable(char ch)
  {
    return (ch <= 'u' && (ch < '\t' || ch >= 'u'
                               || (ch <= 'u' && ch >= 'u')))
      || getType(ch) == FORMAT;
  }

  /**
   * Converts a Unicode character into its lowercase equivalent mapping.
   * If a mapping does not exist, then the character passed is returned.
   * Note that isLowerCase(toLowerCase(ch)) does not always return true.
   *
   * @param ch character to convert to lowercase
   * @return lowercase mapping of ch, or ch if lowercase mapping does
   *         not exist
   * @see #isLowerCase(char)
   * @see #isUpperCase(char)
   * @see #toTitleCase(char)
   * @see #toUpperCase(char)
   */
  public static char toLowerCase(char ch)
  {
    // Signedness doesn't matter, as result is cast back to char.
    return (char) (ch + lower[readChar(ch) >> 7]);
  }

  /**
   * Converts a Unicode character into its uppercase equivalent mapping.
   * If a mapping does not exist, then the character passed is returned.
   * Note that isUpperCase(toUpperCase(ch)) does not always return true.
   *
   * @param ch character to convert to uppercase
   * @return uppercase mapping of ch, or ch if uppercase mapping does
   *         not exist
   * @see #isLowerCase(char)
   * @see #isUpperCase(char)
   * @see #toLowerCase(char)
   * @see #toTitleCase(char)
   */
  public static char toUpperCase(char ch)
  {
    // Signedness doesn't matter, as result is cast back to char.
    return (char) (ch + upper[readChar(ch) >> 7]);
  }

  /**
   * Converts a Unicode character into its titlecase equivalent mapping.
   * If a mapping does not exist, then the character passed is returned.
   * Note that isTitleCase(toTitleCase(ch)) does not always return true.
   *
   * @param ch character to convert to titlecase
   * @return titlecase mapping of ch, or ch if titlecase mapping does
   *         not exist
   * @see #isTitleCase(char)
   * @see #toLowerCase(char)
   * @see #toUpperCase(char)
   */
  public static char toTitleCase(char ch)
  {
    // As title is short, it doesn't hurt to exhaustively iterate over it.
    for (int i = title.length - 2; i >= 0; i -= 2)
      if (title[i] == ch)
        return title[i + 1];
    return toUpperCase(ch);
  }

  /**
   * Converts a character into a digit of the specified radix. If the radix
   * exceeds MIN_RADIX or MAX_RADIX, or if the result of getNumericValue(ch)
   * exceeds the radix, or if ch is not a decimal digit or in the case
   * insensitive set of 'a'-'z', the result is -1.
   * <br>
   * character argument boundary = [Nd]|U+0041-U+005A|U+0061-U+007A
   *    |U+FF21-U+FF3A|U+FF41-U+FF5A
   *
   * @param ch character to convert into a digit
   * @param radix radix in which ch is a digit
   * @return digit which ch represents in radix, or -1 not a valid digit
   * @see #MIN_RADIX
   * @see #MAX_RADIX
   * @see #forDigit(int, int)
   * @see #isDigit(char)
   * @see #getNumericValue(char)
   */
  public static int digit(char ch, int radix)
  {
    if (radix < MIN_RADIX || radix > MAX_RADIX)
      return -1;
    char attr = readChar(ch);
    if (((1 << (attr & TYPE_MASK))
         & ((1 << UPPERCASE_LETTER)
            | (1 << LOWERCASE_LETTER)
            | (1 << DECIMAL_DIGIT_NUMBER))) != 0)
      {
        // Signedness doesn't matter; 0xffff vs. -1 are both rejected.
        int digit = numValue[attr >> 7];
        return (digit < radix) ? digit : -1;
      }
    return -1;
  }

  /**
   * Returns the Unicode numeric value property of a character. For example,
   * <code>'\u'</code> (the Roman numeral fifty) returns 50.
   *
   * <p>This method also returns values for the letters A through Z, (not
   * specified by Unicode), in these ranges: <code>'u'</code>
   * through <code>'u'</code> (uppercase); <code>'u'</code>
   * through <code>'u'</code> (lowercase); and <code>'u'</code>
   * through <code>'u'</code>, <code>'u'</code> through
   * <code>'u'</code> (full width variants).
   *
   * <p>If the character lacks a numeric value property, -1 is returned.
   * If the character has a numeric value property which is not representable
   * as a nonnegative integer, such as a fraction, -2 is returned.
   *
   * character argument boundary = [Nd]|[Nl]|[No]|U+0041-U+005A|U+0061-U+007A
   *    |U+FF21-U+FF3A|U+FF41-U+FF5A
   *
   * @param ch character from which the numeric value property will
   *        be retrieved
   * @return the numeric value property of ch, or -1 if it does not exist, or
   *         -2 if it is not representable as a nonnegative integer
   * @see #forDigit(int, int)
   * @see #digit(char, int)
   * @see #isDigit(char)
   * @since 1.1
   */
  public static int getNumericValue(char ch)
  {
    // Treat numValue as signed.
    return (short) numValue[readChar(ch) >> 7];
  }

  /**
   * Determines if a character is a ISO-LATIN-1 space. This is only the five
   * characters <code>'\t'</code>, <code>'\n'</code>, <code>'\f'</code>,
   * <code>'\r'</code>, and <code>' '</code>.
   * <br>
   * Java space = U+0020|U+0009|U+000A|U+000C|U+000D
   *
   * @param ch character to test
   * @return true if ch is a space, else false
   * @deprecated Replaced by {@link #isWhitespace(char)}
   * @see #isSpaceChar(char)
   * @see #isWhitespace(char)
   */
  public static boolean isSpace(char ch)
  {
    // Performing the subtraction up front alleviates need to compare longs.
    return ch-- <= ' ' && ((1 << ch)
                           & ((1 << (' ' - 1))
                              | (1 << ('\t' - 1))
                              | (1 << ('\n' - 1))
                              | (1 << ('\r' - 1))
                              | (1 << ('\f' - 1)))) != 0;
  }

  /**
   * Determines if a character is a Unicode space character. This includes
   * SPACE_SEPARATOR, LINE_SEPARATOR, and PARAGRAPH_SEPARATOR.
   * <br>
   * Unicode space = [Zs]|[Zp]|[Zl]
   *
   * @param ch character to test
   * @return true if ch is a Unicode space, else false
   * @see #isWhitespace(char)
   * @since 1.1
   */
  public static boolean isSpaceChar(char ch)
  {
    return ((1 << getType(ch))
            & ((1 << SPACE_SEPARATOR)
               | (1 << LINE_SEPARATOR)
               | (1 << PARAGRAPH_SEPARATOR))) != 0;
  }

  /**
   * Determines if a character is Java whitespace. This includes Unicode
   * space characters (SPACE_SEPARATOR, LINE_SEPARATOR, and
   * PARAGRAPH_SEPARATOR) except the non-breaking spaces
   * (<code>'u'</code>, <code>'u'</code>, and <code>'u'</code>);
   * and these characters: <code>'u'</code>, <code>'u'</code>,
   * <code>'u'</code>, <code>'u'</code>, <code>'u'</code>,
   * <code>'u'</code>, <code>'u'</code>, <code>'u'</code>,
   * and <code>'u'</code>.
   * <br>
   * Java whitespace = ([Zs] not Nb)|[Zl]|[Zp]|U+0009-U+000D|U+001C-U+001F
   *
   * @param ch character to test
   * @return true if ch is Java whitespace, else false
   * @see #isSpaceChar(char)
   * @since 1.1
   */
  public static boolean isWhitespace(char ch)
  {
    int attr = readChar(ch);
    return ((((1 << (attr & TYPE_MASK))
              & ((1 << SPACE_SEPARATOR)
                 | (1 << LINE_SEPARATOR)
                 | (1 << PARAGRAPH_SEPARATOR))) != 0)
            && (attr & NO_BREAK_MASK) == 0)
      || (ch <= 'u' && ((1 << ch)
                             & ((1 << '\t')
                                | (1 << '\n')
                                | (1 << 'u')
                                | (1 << 'u')
                                | (1 << '\r')
                                | (1 << 'u')
                                | (1 << 'u')
                                | (1 << 'u')
                                | (1 << 'u'))) != 0);
  }

  /**
   * Determines if a character has the ISO Control property.
   * <br>
   * ISO Control = [Cc]
   *
   * @param ch character to test
   * @return true if ch is an ISO Control character, else false
   * @see #isSpaceChar(char)
   * @see #isWhitespace(char)
   * @since 1.1
   */
  public static boolean isISOControl(char ch)
  {
    return getType(ch) == CONTROL;
  }

  /**
   * Returns the Unicode general category property of a character.
   *
   * @param ch character from which the general category property will
   *        be retrieved
   * @return the character category property of ch as an integer
   * @see #UNASSIGNED
   * @see #UPPERCASE_LETTER
   * @see #LOWERCASE_LETTER
   * @see #TITLECASE_LETTER
   * @see #MODIFIER_LETTER
   * @see #OTHER_LETTER
   * @see #NON_SPACING_MARK
   * @see #ENCLOSING_MARK
   * @see #COMBINING_SPACING_MARK
   * @see #DECIMAL_DIGIT_NUMBER
   * @see #LETTER_NUMBER
   * @see #OTHER_NUMBER
   * @see #SPACE_SEPARATOR
   * @see #LINE_SEPARATOR
   * @see #PARAGRAPH_SEPARATOR
   * @see #CONTROL
   * @see #FORMAT
   * @see #PRIVATE_USE
   * @see #SURROGATE
   * @see #DASH_PUNCTUATION
   * @see #START_PUNCTUATION
   * @see #END_PUNCTUATION
   * @see #CONNECTOR_PUNCTUATION
   * @see #OTHER_PUNCTUATION
   * @see #MATH_SYMBOL
   * @see #CURRENCY_SYMBOL
   * @see #MODIFIER_SYMBOL
   * @see #INITIAL_QUOTE_PUNCTUATION
   * @see #FINAL_QUOTE_PUNCTUATION
   * @since 1.1
   */
  public static int getType(char ch)
  {
    return readChar(ch) & TYPE_MASK;
  }

  /**
   * Converts a digit into a character which represents that digit
   * in a specified radix. If the radix exceeds MIN_RADIX or MAX_RADIX,
   * or the digit exceeds the radix, then the null character <code>'\0'</code>
   * is returned.  Otherwise the return value is in '0'-'9' and 'a'-'z'.
   * <br>
   * return value boundary = U+0030-U+0039|U+0061-U+007A
   *
   * @param digit digit to be converted into a character
   * @param radix radix of digit
   * @return character representing digit in radix, or '\0'
   * @see #MIN_RADIX
   * @see #MAX_RADIX
   * @see #digit(char, int)
   */
  public static char forDigit(int digit, int radix)
  {
    if (radix < MIN_RADIX || radix > MAX_RADIX
        || digit < 0 || digit >= radix)
      return '\0';
    return Number.digits[digit];
  }

  /**
   * Returns the Unicode directionality property of the character. This
   * is used in the visual ordering of text.
   *
   * @param ch the character to look up
   * @return the directionality constant, or DIRECTIONALITY_UNDEFINED
   * @see #DIRECTIONALITY_UNDEFINED
   * @see #DIRECTIONALITY_LEFT_TO_RIGHT
   * @see #DIRECTIONALITY_RIGHT_TO_LEFT
   * @see #DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC
   * @see #DIRECTIONALITY_EUROPEAN_NUMBER
   * @see #DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR
   * @see #DIRECTIONALITY_EUROPEAN_NUMBER_TERMINATOR
   * @see #DIRECTIONALITY_ARABIC_NUMBER
   * @see #DIRECTIONALITY_COMMON_NUMBER_SEPARATOR
   * @see #DIRECTIONALITY_NONSPACING_MARK
   * @see #DIRECTIONALITY_BOUNDARY_NEUTRAL
   * @see #DIRECTIONALITY_PARAGRAPH_SEPARATOR
   * @see #DIRECTIONALITY_SEGMENT_SEPARATOR
   * @see #DIRECTIONALITY_WHITESPACE
   * @see #DIRECTIONALITY_OTHER_NEUTRALS
   * @see #DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING
   * @see #DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE
   * @see #DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING
   * @see #DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE
   * @see #DIRECTIONALITY_POP_DIRECTIONAL_FORMAT
   * @since 1.4
   */
  public static byte getDirectionality(char ch)
  {
    // The result will correctly be signed.
    return (byte) (direction[readChar(ch) >> 7] >> 2);
  }

  /**
   * Determines whether the character is mirrored according to Unicode. For
   * example, <code>u</code> (LEFT PARENTHESIS) appears as '(' in
   * left-to-right text, but ')' in right-to-left text.
   *
   * @param ch the character to look up
   * @return true if the character is mirrored
   * @since 1.4
   */
  public static boolean isMirrored(char ch)
  {
    return (readChar(ch) & MIRROR_MASK) != 0;
  }

  /**
   * Compares another Character to this Character, numerically.
   *
   * @param anotherCharacter Character to compare with this Character
   * @return a negative integer if this Character is less than
   *         anotherCharacter, zero if this Character is equal, and
   *         a positive integer if this Character is greater
   * @throws NullPointerException if anotherCharacter is null
   * @since 1.2
   */
  public int compareTo(Character anotherCharacter)
  {
    return value - anotherCharacter.value;
  }

  /**
   * Compares an object to this Character.  Assuming the object is a
   * Character object, this method performs the same comparison as
   * compareTo(Character).
   *
   * @param o object to compare
   * @return the comparison value
   * @throws ClassCastException if o is not a Character object
   * @throws NullPointerException if o is null
   * @see #compareTo(Character)
   * @since 1.2
   */
  public int compareTo(Object o)
  {
    return compareTo((Character) o);
  }
} // class Character
