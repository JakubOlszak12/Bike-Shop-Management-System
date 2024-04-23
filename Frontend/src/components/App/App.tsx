import React, { useState, useEffect } from 'react';

interface Category {
  id: number;
  name: string;
}

export function App() {
  const [categories, setCategories] = useState<Category[]>([]);

  useEffect(() => {
    // Fetch categories from the API
    fetch('http://localhost:8080/api/category/getAll')
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to fetch categories');
        }
        return response.json();
      })
      .then((data: Category[]) => {
        setCategories(data); // Update categories state with fetched data
      })
      .catch(error => {
        console.error('Error fetching categories:', error.message);
      });
  }, []);

  return (
    <div className="container-fluid">
      <div className="row">
        <div className="col-md-3">
          {/* Left navigation bar */}
          <nav className="navbar navbar-expand-md navbar-dark bg-dark">
            <div className="container-fluid">
              <div className="collapse navbar-collapse">
                <ul className="navbar-nav">
                  {categories.map(category => (
                    <li className="nav-item" key={category.id}>
                      <a className="nav-link" href="#">{category.name}</a>
                    </li>
                  ))}
                </ul>
              </div>
            </div>
          </nav>
        </div>
        <div className="col-md-9">
        
          <div className="center">
           
          </div>
        </div>
      </div>
    </div>
  );
}
