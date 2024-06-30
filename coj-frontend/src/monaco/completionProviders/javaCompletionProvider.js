import Java20Lexer from "../javaParser/Java20Lexer";
import Java20Parser from "../javaParser/Java20Parser";
import { CommonTokenStream, InputStream } from "antlr4";

const javaCompletionProvider = (monaco) => {

  const arrayListMethods = [
    {
      label: "add",
      kind: monaco.languages.CompletionItemKind.Method,
      insertText: "add(${1:element})",
      insertTextRules:
        monaco.languages.CompletionItemInsertTextRule.InsertAsSnippet,
      documentation:
        "Inserts the specified element at the specified position in this list.",
    },
    {
      label: "get",
      kind: monaco.languages.CompletionItemKind.Method,
      insertText: "get(${1:index})",
      insertTextRules:
        monaco.languages.CompletionItemInsertTextRule.InsertAsSnippet,
      documentation:
        "Returns the element at the specified position in this list.",
    },
    {
      label: "size",
      kind: monaco.languages.CompletionItemKind.Method,
      insertText: "size()",
      documentation: "Returns the number of elements in this list.",
    },
  ];

  return {
    provideCompletionItems: (model, position) => {
      console.log("方法调用")
      const textUntilPosition = model.getValueInRange({
        startLineNumber: 1,
        startColumn: 1,
        endLineNumber: position.lineNumber,
        endColumn: position.column,
      });

      try {
        const inputStream = new InputStream(textUntilPosition);
        const lexer = new Java20Lexer(inputStream);
        const tokenStream = new CommonTokenStream(lexer);
        const parser = new Java20Parser(tokenStream);

        // 移除默认的错误监听器
        parser.removeErrorListeners();
        lexer.removeErrorListeners();
        // 添加自定义的错误监听器，忽略解析错误
        const errorListener = {
          syntaxError: (recognizer, offendingSymbol, line, column, msg, e) => {
            console.warn(`Syntax error at line ${line}:${column} - ${msg}`);
          },
        };

        parser.addErrorListener(errorListener);
        lexer.addErrorListener(errorListener);

        parser.buildParseTrees = true;
        const tree = parser.compilationUnit();

        const isArrayListInstance = checkIfArrayListInstance(tree, position);

        if (isArrayListInstance) {
          return { suggestions: arrayListMethods };
        }
      } catch (error) {
        console.error("Error during parsing:", error);
      }
      return { suggestions: [] };
    },
  };
};

const checkIfArrayListInstance = (tree, position) => {
  let isArrayList = false;

  // 解析语法树以确定当前位置的上下文是否是 ArrayList 实例
  const findArrayListInstance = (node) => {
    if (node.getText().includes("ArrayList") && node.getText().includes(".")) {
      isArrayList = true;
    }
  };

  const recursiveWalk = (node) => {
    if (!node) return;

    findArrayListInstance(node);

    if (node.children && node.children.length > 0) {
      node.children.forEach((child) => recursiveWalk(child));
    }
  };

  recursiveWalk(tree);
  return isArrayList;
};

export default javaCompletionProvider;
