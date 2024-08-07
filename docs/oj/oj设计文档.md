# OJ 设计文档

## 1. 判题

## 2. 代码库

对于一个 OJ 来说，题目 / 测试数据就是这个 OJ 的最重要资产。但是除了题目和测试数据以外，代码库也是一个 OJ 不应该忽视的重要资产。

应该怎么描述这个代码库呢？

类似于 C++ 提供的 STL，Java 的集合，Python 的标准库等等...在很多不同的算法题中，总是会用到一些类似的基础代码。例如，将题目所给的数据构造成一颗二叉树，或者建造成一个图等等。然后再对构造出来的对象进行下一步操作。

像这样的通用代码，能将其组织起来，方便用户学习、查阅，复制，修改等等，也可以作为一个不错的功能。

所以设计这个功能时，有以下几个点需要考虑。

我认为最重要的点，是「方便获取」。

例如，用户在做题界面，想要查看这个代码库中的某一段代码时，最快速的做法就是，在这个界面点击一个快捷键。然后弹出一个模态框（注意，一定是模态框，因为模态框不需要跳转页面，不会打断用户当前写代码的流程），然后自动 focus 到一个输入框，用户输入一些关键词，或者标签（搜索功能要做好，同时也可以接受用户反馈，对于某个代码片段，应该添加什么标签比较合适），就能定位到某个代码段。然后用户可以快速的复制代码，然后关闭当前的模态框。

