import React, {useEffect, useState} from 'react';

export default function App(){
  const [sweets, setSweets] = useState([]);
  useEffect(()=>{ fetch('/api/sweets').then(r=>r.json()).then(setSweets); },[]);
  return (
    <div style={{padding:20,fontFamily:'Arial'}}>
      <h1>Sweet Shop</h1>
      <div>
        {sweets.map(s=>(
          <div key={s.id} style={{border:'1px solid #ccc', padding:10, margin:10}}>
            <h3>{s.name} ({s.category})</h3>
            <div>Price: ₹{s.price} | Qty: {s.quantity}</div>
            <button disabled={s.quantity===0}>Purchase</button>
          </div>
        ))}
      </div>
    </div>
  );
}
