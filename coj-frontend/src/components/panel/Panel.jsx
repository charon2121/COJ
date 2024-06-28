import React from "react";
import styles from "./Panel.module.css";

function Panel(props) {
  return <div className={styles.panel}>{props.children}</div>;
}

export default Panel;
