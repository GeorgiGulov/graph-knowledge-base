import React, {FC, useState} from 'react';

export interface MuRadioButtonProps {
    type: string
    onChange: (str: string) => void
}

const MuRadioButton: FC<MuRadioButtonProps> = (props) => {
    const [type, setType] = useState('none')

    return (
        <div style={{display: "flex"}}>

            <div style={{display: "flex"}}>
                <input type="radio" name="radio" value="Отключить CRUD"
                       checked={type == 'none'}
                       onChange={() => {
                           props.onChange('none')
                           setType('none')
                       }}/>
                <div>Отключить CRUD</div>
            </div>

            <div style={{display: "flex"}}>
                <input type="radio" name="radio" value="Редактировать имя вершины"
                       checked={type == 'editNode'}
                       onChange={() => {
                           props.onChange('editNode')
                           setType('editNode')
                       }}/>
                <div>Редактировать имя вершины</div>
            </div>

            <div style={{display: "flex"}}>
                <input type="radio" name="radio" value="Редактировать имя ребра"
                       checked={type == 'editEdge'}
                       onChange={() => {
                           props.onChange('editEdge')
                           setType('editEdge')
                       }}/>
                <div>Редактировать имя ребра</div>
            </div>

            <div style={{display: "flex"}}>
                <input type="radio" name="radio" value="Добавить поисковую вершину"
                       checked={type == 'addSearch'}
                       onChange={() => {
                           props.onChange('addSearch')
                           setType("addSearch")
                       }}/>
                <div>Добавить поисковую вершину</div>
            </div>

            <div style={{display: "flex"}}>
                <input type="radio" name="radio" value="Добавить поискове ребро"
                       checked={type == 'addEdge'}
                       onChange={() => {
                           props.onChange('addEdge')
                           setType('addEdge')
                       }}/>
                <div>Добавить поискове ребро</div>
            </div>

            <div style={{display: "flex"}}>
                <input type="radio" name="radio" value="Добавить вершину с данными"
                       checked={type == 'addData'}
                       onChange={() => {
                           props.onChange('addData')
                           setType('addData')
                       }}/>
                <div>Добавить вершину с данными</div>
            </div>

            <div style={{display: "flex"}}>
                <input type="radio" name="radio" value="Удалить вершину"
                       checked={type == 'deleteNode'}
                       onChange={() => {
                           props.onChange('deleteNode')
                           setType('deleteNode')
                       }}/>
                <div>Удалить вершину</div>
            </div>

            <div style={{display: "flex"}}>
                <input type="radio" name="radio" value="Удалить ребро"
                       checked={type == 'deleteEdge'}
                       onChange={() => {
                           props.onChange('deleteEdge')
                           setType('deleteEdge')
                       }}/>
                <div>Удалить ребро</div>
            </div>

        </div>
    );
};

export default MuRadioButton;