# Simple Chinese IRSystem

A demo program for Chinese information retreival.  
When given a Chinese keyword, it will search the text documents and output the IDs of five most related documents.
The search results are ranked based on TF/IDF weighting.

# Result Display
The search results are displayed in the following form:

     Doc ID, TF/IDF score of the keyword in this document

For example:

     Input: 汽车 (car)

     Output:

     doc10,0.06924109031275924

     doc1, 0.027245958817024653

     doc11,0.026535027411581102

     doc9, 0.01567646987659992

     doc4, 0.014135937412525201

# Document Format

The documents are stored in txt files and follow the follwing format:

\<docid> document ID \<docid>

\<doc> document content 
\<doc>

# Chinese Word Segmentation
The Chinese word segmenter used for the documents is lucene-core-3.6.0 + IKAnalyzer2012.
