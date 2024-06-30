import React, { useEffect, useRef, useState } from "react";
import styles from "./Problem.module.css";
import MonacoEditor, { loader } from "@monaco-editor/react";
import { message, Tabs } from "antd";
import { language as javaLanguage } from "monaco-editor/dev/vs/basic-languages/java/java";
import { listen } from "vscode-ws-jsonrpc";
import {
  MonacoLanguageClient,
  createConnection
} from "monaco-languageclient";

import 'vscode/localExtensionHost'; // 确保正确导入

function createLanguageClient(connection, monaco) {
  return new MonacoLanguageClient({
    name: "Monaco Language Client",
    clientOptions: {
      documentSelector: ['java'],
      errorHandler: {
        error: () => {
          console.error("An error occurred in the Language Client");
          return monaco.languageclient.ErrorAction.Continue;
        },
        closed: () => {
          console.error("The Language Client connection was closed");
          return monaco.languageclient.CloseAction.DoNotRestart;
        }
      }
    },
    connectionProvider: {
      get: (errorHandler, closeHandler) => {
        return Promise.resolve(connection);
      }
    }
  });
}

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

    function createWebSocket(url) {
      const webSocket = new WebSocket(url);
      webSocket.onopen = () => {
        console.log('WebSocket connection opened');
      };
      webSocket.onclose = () => {
        console.log('WebSocket connection closed');
      };
      webSocket.onerror = (error) => {
        console.log('WebSocket error', error);
      };
      return webSocket;
    }

    const url = 'ws://localhost:3002'; // 确保这是你的 JDT Language Server 的 WebSocket 地址
    const webSocket = createWebSocket(url);

    listen({
      webSocket,
      onConnection: (connection) => {
        const languageClient = createLanguageClient(connection, monaco);
        const disposable = languageClient.start();
        connection.onClose(() => disposable.dispose());
      }
    });
  };

  useEffect(() => {
    loader.init().then((monaco) => {
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
    });

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
        }}
      >
        <MonacoEditor
          height="90vh"
          language="java"
          value="// hello world"
          onMount={handleEditorDidMount}
        />
      </div>
    </div>
  );
}

export default Problem;
