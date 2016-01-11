# A Simple Tool for Chinese Information Retrieval

This tool (demo) is intended for retrieving information in corpus.
When a keyword is input, this tool will search and rank the matching documents in the corpus according to their relevance to the given query. The degree of relevance is estimated based on TF/IDF weighting.

# Result Display
The search results are displayed as follows:

     Document ID, TF/IDF score of the keyword in this document

For example:

     Input: 汽车 (car)

     Output:

     doc10,0.06924109031275924

     doc1, 0.027245958817024653

     doc11,0.026535027411581102

     doc9, 0.01567646987659992

     doc4, 0.014135937412525201

# Document Format

The documents are stored in .txt files and arranged in the following format:

           <docid> document ID <docid>
           <doc> document content <doc>

# Chinese Word Segmentation
The Chinese word segmenter used for processing documents is lucene-core-3.6.0 + IKAnalyzer2012.
