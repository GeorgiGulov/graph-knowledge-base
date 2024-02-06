import React, {FC, FunctionComponent, PropsWithChildren, useState} from 'react';

interface CardProps {
    width: string,
    height: string,
    onClick: (count: number) => void
}

const Card: FC<PropsWithChildren<CardProps>> = props => {
    const [count, setCount] = useState(0)

    return (
        <div
            style={{width: props.width, height: props.height, border: "2px solid"}}
            onClick={()=>props.onClick(count)
        }>
            {props.children}
        </div>
    );
};

export default Card;