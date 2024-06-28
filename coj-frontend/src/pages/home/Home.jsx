import React from "react";
import Container from "../../components/container/Container";
import LeftPane from "../../components/container/leftpane/LeftPane";
import RightPane from "../../components/container/rightpane/RightPane";
import Panel from "../../components/panel/Panel";
import { Carousel } from "antd";

function Home(props) {
  const contentStyle = {
    margin: 0,
    height: "265px",
    color: "#fff",
    lineHeight: "265px",
    textAlign: "center",
    background: "#364d79",
  };

  return (
    <Container>
      <LeftPane>
        <Panel>
          <Carousel
            autoplay={true}
            arrows={true}
          >
            <div>
              <h3 style={contentStyle}>1</h3>
            </div>
            <div>
              <h3 style={contentStyle}>2</h3>
            </div>
            <div>
              <h3 style={contentStyle}>3</h3>
            </div>
            <div>
              <h3 style={contentStyle}>4</h3>
            </div>
            <div>
              <h3 style={contentStyle}>5</h3>
            </div>
          </Carousel>
        </Panel>
      </LeftPane>
      <RightPane>
        <Panel>hom32</Panel>
      </RightPane>
    </Container>
  );
}

export default Home;
