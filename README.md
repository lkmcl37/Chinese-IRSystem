# Implementation of TF/IDF for information retrieval

A simple implementation of TF/IDF for retrieving information in corpus.
When a keyword is input, this program will search and display the matching documents in the corpus according to their relevance to the given query. The degree of relevance is estimated based on TF/IDF weighting.

# Result Display
The search results are displayed in the following format:

     Document ID, TF/IDF score of the keyword in this document

Usage example:

     Input: 汽车 (car)

     Output:

     doc10,0.06924109031275924

     doc1, 0.027245958817024653

     doc11,0.026535027411581102

     doc9, 0.01567646987659992

     doc4, 0.014135937412525201

# Corpus Format

The corpus is stored in .txt files and arranged in the following format:

           <docid> document ID <docid>
           <doc> document content <doc>

# Chinese Word Segmentation
The Chinese word segmenter used for processing documents is lucene-core-3.6.0 + IKAnalyzer2012.
