# coj-frontend

## 技术选型

1. react18
2. react-router-dom 6.4.2
3. redux
4. monaco-editor

组件库：antd 18


## 文件结构

```
.
├── App.css
├── App.js
├── api  // 网络请求
├── components  // 通用组件
│   ├── container
│   ├── layout
│   ├── navbar
│   └── panel
├── context  // 上下文管理
├── index.css
├── index.js
├── monaco   // 代码编辑器相关的资源，例如关键字补全逻辑，语法检查逻辑，编辑器主题等...
├── pages    // 页面
│   ├── home    // home 页面及其页面上的组件
│   ├── problem   // ...
│   └── problemSet   // ...
├── redux  // 全局状态管理
├── router   // 路由文件
│   └── index.jsx
└── utils    // 工具函数
    └── 1.js
```

## 代码编辑器

monaco-editor

monaco-editor 有三个不同的选择

第一个选择是微软直接提供的原生的编辑器，

[monaco-editor](https://github.com/microsoft/monaco-editor)

另外两个是在 monaco-editor 的基础上封装的编辑器

[monaco-react](https://github.com/suren-atoyan/monaco-react)
和 [monaco-editor-react](https://github.com/react-monaco-editor/react-monaco-editor)

