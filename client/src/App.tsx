import React from 'react';
import Card from "./components/test/Card";
import Graph from "./components/graphQuery/Graph";
import NodeInfo from "./components/nodeInfo/NodeInfo";

const App = () => {
    return (
        <div>
            {/*<Card*/}
            {/*    width={"400px"}*/}
            {/*    height={"400px"}*/}
            {/*    onClick={(count) => console.log(count)}*/}
            {/*    children={*/}
            {/*        <div>*/}
            {/*            <button>111</button>*/}
            {/*        </div>*/}
            {/*    }*/}
            {/*/>*/}
            <Graph/>
            <NodeInfo/>

        </div>);
};

export default App;