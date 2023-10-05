import React, { useState } from "react";
function SelectMultiplo(props) {
    const [val, setVal] = useState('')
    const data = ["Ford", "Hyundai", "Mercedes-Benz", "Volkswagen", "Toyota"];
    return (
        <div className="form-group">
            <select value={props.value} onChange={e => props.onChange(e)} className="form-control">
                {
                    data.map(opt => <option>{opt}</option>)
                }
            </select>
        </div>

    );
}
export default SelectMultiplo;