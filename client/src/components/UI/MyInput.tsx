import React, {FC, PropsWithChildren} from 'react';
import MyButton from "./MyButton";

export interface MyInputProps{
    placeholder: string
    value: string,
    onChange: (str: string)=>void
}

const MyInput: FC<MyInputProps> = (props: MyInputProps) => {
    return (
        <input
            type="text"
            value={props.value}
            onChange={(event)=>{
                props.onChange(event.target.value)
            }}
            placeholder={props.placeholder}
        />
    );
};

export default MyInput;