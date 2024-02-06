import {useAppDispatch, useAppSelector} from "../../hooks/redux";
import {graphSlice} from "../../store/reducers/GraphSlice";
import {IProperty} from "../../entity/graphQuery/IProperty";
import {INode} from "../../entity/graphQuery/INode";
import PropertyInfoInput from "./PropertyInfoInput";
import MyButton from "../UI/MyButton";


const NodeInfo = () => {
    const nodeInfoId = useAppSelector(state => state.nodeInfoReducer.nodeInfoId)
    const listNodes = useAppSelector(state => state.graphReducer.nodes)
    const changeNode = graphSlice.actions.changeNode

    const dispatch = useAppDispatch()

    const nodeInfo = listNodes.find((node) => node.id == nodeInfoId)
    console.log("nodeInfo:", nodeInfo)

    return (
        <div>
            <div>{nodeInfo?.label}</div>
            {
                nodeInfo?.property.map((it) => 
                    <div style={{display: "flex"}}>
                        <div key={it.id}> {it.label}: {it.value}</div>

                        <MyButton description={"Удалить"} onClick={()=>{

                            if (nodeInfo != null) {

                                const newNode: INode = {
                                    id: nodeInfo.id,
                                    label: nodeInfo.label,
                                    property: nodeInfo.property.filter((property)=> property.id != it.id)
                                }

                                dispatch(changeNode(newNode))
                            }
                            console.log()
                        }}/>
                    </div>
                )
            }

            <PropertyInfoInput onClick={(key, value) => {
                const id = crypto.randomUUID().toString()

                const property: IProperty = {
                    id: id,
                    label: key,
                    value: value
                }

                if (nodeInfo != null) {

                    const newNode: INode = {
                        id: nodeInfo.id,
                        label: nodeInfo.label,
                        property: [...nodeInfo.property].concat(property)
                    }

                    dispatch(changeNode(newNode))
                }
            }}/>

        </div>
    );
};

export default NodeInfo;