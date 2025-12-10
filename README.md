# CSC 207: Text Editor

**Author**: Jiayi Zhang

## Resources Used

+ Text editor: VS Code 1.101.2
+ Java version: "17.0.16"
+ I referred to "docs.oracle.com/javase/8/docs/api/javax/swing/KeyStroke.html"
+ for unfamiliar methods including KeyStroke and Files.writeString.

## Changelog
 
Revision: I added spaces between ")" and "]" for every if statement or loops in both the SimpleStringBuffer file and the gapBuffer file. I also added a public SimpleStringBuffer() constructor to fix the compilation error.
re


Insert:

1. The relevant input is char ch.
2. The critical operation that controls the time complexity is data[i+1] = data[i];
3. Let T be the runtime function of insert:
    T(n) = n-k, where k = position of cursor, n = size of data.

    Best case: k = n (cursor at the end), hence T(n)=0 -> O(1).
    Worst case: k = 0 (cursor at the start), hence T(n)=n -> O(n).
    Amortized: O(n)

4. The time complexity is O(n)/linear.


Our runtime model accounts for the two main operations that insert performs: checking and 
(possibly) expanding the buffer, and shifting characters to make room for the new one. Expanding
the buffer happens only occasionally and takes O(n) time, but because it doesn't occurs all the 
time, its amortized cost per insert is O(1). The dominant cost comes from shifting the existing 
characters to the right, which requires n−k operations where k is the cursor position. 
This leads to a worst-case runtime of O(n). Using a mutable char[] allows these operations 
to be done in place; if we instead used immutable String, each insertion would require 
creating and copying a new string, which would be far more cost-expensive.
