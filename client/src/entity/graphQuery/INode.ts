import {IProperty} from "./IProperty";


export interface INode {
    id: string
    label: string,
    property: IProperty[]
}