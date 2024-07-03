import React, { useRef, useState } from "react";
import styles from "./Problem.module.css";
import MonacoEditor from "@monaco-editor/react";
import { Tabs } from "antd";
import { language as javaLanguage } from "monaco-editor/dev/vs/basic-languages/java/java";


function Problem(props) {
  const resizerRef = useRef(null);

  const [leftWidth, setLeftWidth] = useState("calc(50% - 4px)");
  const [rightWidth, setRightWidth] = useState("calc(50% - 4px)");
  const [isResizing, setIsResizing] = useState(false);

  const handleMouseDown = () => {
    setIsResizing(true);
  };

  const handleMouseMove = (event) => {
    if (!isResizing) return;
    const containerWidth =
      resizerRef.current.parentElement.getBoundingClientRect().width;
    const leftPaneWidth =
      event.clientX -
      resizerRef.current.parentElement.getBoundingClientRect().left;
    const rightPaneWidth = containerWidth - leftPaneWidth;

    setLeftWidth(`${leftPaneWidth}px`);
    setRightWidth(`${rightPaneWidth}px`);
  };

  const handleMouseUp = () => {
    setIsResizing(false);
  };

  const onChange = (key) => {
    console.log(key);
  };

  const items = [
    {
      key: "1",
      label: "Tab 1",
      children: "Content of Tab Pane 1",
    },
    {
      key: "2",
      label: "Tab 2",
      children: "Content of Tab Pane 2",
    },
    {
      key: "3",
      label: "Tab 3",
      children: "Content of Tab Pane 3",
    },
  ];

  const editorRef = useRef(null);
  const monacoRef = useRef(null);

  const handleEditorDidMount = (editor, monaco) => {
    editorRef.current = editor;
    monacoRef.current = monaco;

    monaco.languages.register({ id: "java" });
    monaco.languages.setMonarchTokensProvider("java", javaLanguage);
    monaco.languages.registerCompletionItemProvider("java", {
      provideCompletionItems: (model, position) => {
        let suggestions = [];

        javaLanguage.keywords.forEach((keyword) => {
          suggestions.push({
            label: keyword,
            kind: monaco.languages.CompletionItemKind.Keyword,
            insertText: keyword,
          });
        });

        javaLanguage.operators.forEach((operator) => {
          suggestions.push({
            label: operator,
            kind: monaco.languages.CompletionItemKind.Operator,
            insertText: operator,
          });
        });

        return {
          suggestions: suggestions,
        };
      },
    });
  };

  return (
    <div
      className={styles.container}
      onMouseMove={handleMouseMove}
      onMouseUp={handleMouseUp}
      onMouseLeave={handleMouseUp}
    >
      <div className={styles.leftPane} style={{ width: leftWidth }}>
        <Tabs defaultActiveKey="1" items={items} onChange={onChange} />
      </div>
      <div
        className={styles.resizer}
        ref={resizerRef}
        onMouseDown={handleMouseDown}
      ></div>
      <div
        className={styles.rightPane}
        style={{
          width: rightWidth,
        }}
      >
        <MonacoEditor
          height="500px"
          language="java"
          value="// hello world"
          onMount={handleEditorDidMount}
        />
      </div>
    </div>
  );
}

export default Problem;
