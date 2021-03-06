package com.github.vickumar1981.stringdistance.impl

import com.github.vickumar1981.stringdistance.interfaces.NGramTokenizer

trait NGramImpl extends NGramTokenizer {
  protected def nGram(s1: String, s2: String, n: Int = 1): Double = {
    require(n > 0, "Ngram score, n-gram size must be a positive number.")
    foldNGram(s1, s2, n)(0d)(_ => 1d) {
      (s1Tok, s2Tok, dist) => 1 - dist.toDouble / math.max(s1Tok.length, s2Tok.length)
    }
  }

  protected def nGramDist(s1: String, s2: String, n: Int = 1): Int = {
    require(n > 0, "Ngram distance, n-gram size must be a positive number.")
    foldNGram(s1, s2, n)(0)(identity) {
      (_, _, dist) => dist
    }
  }
}
