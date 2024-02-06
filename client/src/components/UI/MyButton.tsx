import React, {FC, PropsWithChildren} from 'react';

export interface MyButtonProps {
    description: string
    onClick: () => void
}

const MyButton: FC<MyButtonProps> = (props: MyButtonProps) => {
    return (
        <button onClick={props.onClick}>{props.description}</button>
    );
};

export default MyButton;