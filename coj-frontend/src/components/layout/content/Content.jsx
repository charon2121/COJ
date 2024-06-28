import React from "react";
import styles from "./Content.module.css";

function Content(props) {
  return (
    <main className={styles.content}>
      <div className={styles.scrollContent}>{props.children}</div>
    </main>
  );
}

export default Content;
