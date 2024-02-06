import React, {FC, useState} from 'react';
import MyInput from "../UI/MyInput";
import MyButton from "../UI/MyButton";

export interface PropertyInfoInputProps{
    onClick: (key: string, value: string) => void
}


const PropertyInfoInput: FC<PropertyInfoInputProps> = (props) => {
    const [input1, setInput1] = useState("")
    const [input2, setInput2] = useState("")

    return (
        <div>
            <MyInput value={input1} placeholder="название" onChange={setInput1}/>
            <MyInput value={input2} placeholder="значение" onChange={setInput2}/>
            <MyButton description="Добавить" onClick={() =>{
                console.log(input1, input2)
                props.onClick(input1, input2)
            } }/>
        </div>
    );
};

export default PropertyInfoInput;