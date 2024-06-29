import React, { useEffect, useRef, useState } from "react";
import styles from "./Problem.module.css";
import MonacoEditor from "@monaco-editor/react";
import { message, Tabs } from "antd";
import "../../test.css";
import JavaCompletionProvider from "../../monaco/completionProviders/javaCompletionProvider";

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

  // 注册补全项提供器
  const handleEditorDidMount = (editor, monaco) => {
    editorRef.current = editor;
    monacoRef.current = monaco;
    const { provideCompletionItems } = JavaCompletionProvider(monaco);
    // 注册补全项提供器
    monaco.languages.registerCompletionItemProvider(
      "java",
      provideCompletionItems,
    );
  };

  useEffect(() => {
    const handleKeyDown = (event) => {
      if ((event.ctrlKey || event.metaKey) && event.key === "s") {
        event.preventDefault(); // 阻止默认行为（例如保存页面）
        message.success("保存代码到本地");
      }
    };

    document.addEventListener("keydown", handleKeyDown);

    // 清除事件监听器以避免内存泄漏
    return () => {
      document.removeEventListener("keydown", handleKeyDown);
    };
  }, []);

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
          fontFamily: "MyMenlo2",
        }}
      >
        <MonacoEditor
          height="90vh"
          language="java"
          value="// type your code here"
          onMount={handleEditorDidMount}
          options={{
            fontSize: 16, // 设置字体大小
            fontFamily: "MyMenlo2",
          }}
        />
      </div>
    </div>
  );
}

export default Problem;
